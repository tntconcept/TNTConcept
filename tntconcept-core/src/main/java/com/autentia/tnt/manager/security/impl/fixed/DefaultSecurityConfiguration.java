/**
 * TNTConcept Easy Enterprise Management by Autentia Real Bussiness Solution S.L.
 * Copyright (C) 2007 Autentia Real Bussiness Solution S.L.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.autentia.tnt.manager.security.impl.fixed;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.acegisecurity.GrantedAuthority;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.autentia.tnt.dao.ITransferObject;
import com.autentia.tnt.manager.security.Permission;
import com.autentia.tnt.manager.security.Principal;
import com.autentia.tnt.manager.security.exception.SecConfigException;
import com.autentia.tnt.manager.workflow.Field;
import com.autentia.tnt.manager.workflow.State;
import com.autentia.tnt.manager.workflow.WorkFlow;
import com.autentia.tnt.util.ConfigurationUtil;
import com.autentia.tnt.xml.DOMUtils;

/**
 * This class holds all security configuration for the "default fixed security"
 * of TNTConcept.
 * 
 * @author Ivan Zaera Avellon
 */
public final class DefaultSecurityConfiguration implements
		ISecurityConfiguration, IWorkFlowConfiguration {

	private Map<Integer, GrantedAuthority[]> rolePermissions;
	private Map<AclMatrixKey, AclMatrixValue> writeMatrix;
	private Map<AclMatrixKey, AclMatrixValue> deleteMatrix;
	private Map<AclMatrixKey, AclMatrixValue> readMatrix;
	private Map<String, WorkFlow> workFlowMatrix;
	private Map<FieldAclMatrixKey, Boolean> detailViewMatrix;

	private int roleAdminId;
	private int roleSupervisorId;
	private int roleStaffId;
	private int roleUserId;
	private int roleClientId;
	//TODO atributo para el nuevo ROL
	private int roleProjectManagerId;

	private static final Log logger = LogFactory
			.getLog(DefaultSecurityConfiguration.class);

	/**
	 * Constructor
	 * 
	 * @param cfg
	 */
	public DefaultSecurityConfiguration(ConfigurationUtil cfg) {
		try {

			String path2File = cfg.getConfigDir() + cfg.getSecurityMatrix();
			File confFile = new File(path2File);
			Map<GrantedAuthority, boolean[]> permissionsMap = null;

			if (!confFile.exists()) {
				throw new SecConfigException(
						"Fichero de configuración de seguridad no encontrado:"
								+ path2File);
			}

			logger.info("Loading ACEGI configuration from " + confFile);

			this.roleAdminId = cfg.getRoleAdminId();
			this.roleSupervisorId = cfg.getRoleSupervisorId();
			this.roleStaffId = cfg.getRoleStaffId();
			this.roleUserId = cfg.getRoleUserId();
			this.roleClientId = cfg.getRoleClientId();
			//TODO RECUPERAR EL ID DEL NUEVO ROL
			this.roleProjectManagerId = cfg.getRoleProjectManagerId();

			this.readMatrix = new HashMap<AclMatrixKey, AclMatrixValue>();
			this.writeMatrix = new HashMap<AclMatrixKey, AclMatrixValue>();
			this.deleteMatrix = new HashMap<AclMatrixKey, AclMatrixValue>();
			this.rolePermissions = new HashMap<Integer, GrantedAuthority[]>();
			this.workFlowMatrix = new HashMap<String, WorkFlow>();
			this.detailViewMatrix = new HashMap<FieldAclMatrixKey, Boolean>();
			permissionsMap = new HashMap<GrantedAuthority, boolean[]>();

			DocumentBuilderFactory domFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder domBuilder = domFactory.newDocumentBuilder();
			org.w3c.dom.Document document = domBuilder.parse(confFile);

			this.loadEntities(document, permissionsMap);
			this.loadActions(document, permissionsMap);
			this.loadFlows(document, workFlowMatrix);
			this.readMatrix = Collections.unmodifiableMap(this.readMatrix);
			this.writeMatrix = Collections.unmodifiableMap(this.writeMatrix);
			this.deleteMatrix = Collections.unmodifiableMap(this.deleteMatrix);
			this.workFlowMatrix = Collections
					.unmodifiableMap(this.workFlowMatrix);
			this.detailViewMatrix = Collections.unmodifiableMap(this.detailViewMatrix);

			this.rolePermissions.put(roleAdminId, loadPermsArray(
					permissionsMap, 0));
			this.rolePermissions.put(roleSupervisorId, loadPermsArray(
					permissionsMap, 1));
			this.rolePermissions.put(roleStaffId, loadPermsArray(
					permissionsMap, 2));
			this.rolePermissions.put(roleUserId, loadPermsArray(permissionsMap,
					3));
			this.rolePermissions.put(roleClientId, loadPermsArray(
					permissionsMap, 4));
			
			//TODO CARGAR LOS PERMISOS DEL NUEVO ROL 
			this.rolePermissions.put(roleProjectManagerId, loadPermsArray(
					permissionsMap, 4));

			logger.info("ACEGI configuration OK readed" + confFile);
		} catch (Exception ex) {
			logger.fatal(ex); // SAXException, ParserConfigurationException,
			// IOException, SecConfigException
		}
	}

	/**
	 * Inicializa las matrices de privilegios de operaciones de Lectura,
	 * Escritura y Borrado de cada una de las clases.
	 * 
	 * @param permissionsMap
	 */
	private void loadEntities(org.w3c.dom.Document document,
			Map<GrantedAuthority, boolean[]> permissionsMap)
			throws SecConfigException {
		NodeList entities = null;
		Node entity = null;
		String classname = null;
		NodeList operations = null;
		Node operation = null;
		String operationName = null;
		AclMatrixValue admin = null;
		AclMatrixValue super1 = null;
		AclMatrixValue staff = null;
		AclMatrixValue user = null;
		AclMatrixValue cli = null;
		//TODO Añadir la nueva matriz de ACL
		AclMatrixValue projectManager = null;
		Map<AclMatrixKey, AclMatrixValue> targetMatrix = null;

		logger.debug("Reading 'entities'");

		try {
			// Cargamos las entidades
			entities = document.getElementsByTagName("entity");
			for (int i = 0, iCount = entities.getLength(); i < iCount; i++) {
				entity = entities.item(i);
				if ("entity".equals(entity.getNodeName())) {
					classname = DOMUtils.getAttribute(entity, "name");
					
					if (logger.isDebugEnabled()){
						logger.debug("Entity readed: " + classname);
					}
					 
					classname = "com.autentia.tnt.businessobject." + classname;
					operations = entity.getChildNodes();
					for (int j = 0, jCount = operations.getLength(); j < jCount; j++) {
						operation = operations.item(j);

						// <operation name="delete" admin="deny" super="deny"
						// staff="deny" user="deny" cli="deny"/>
						if ("operation".equals(operation.getNodeName())) {
							operationName = DOMUtils.getAttribute(operation,
									"name");

						    if (logger.isDebugEnabled()){
						    	logger.debug("Operation readed: " + operationName);
						    }
						    
							targetMatrix = null;
							if ("read".equals(operationName)) {
								targetMatrix = readMatrix;
							} else if ("write".equals(operationName)) {
								targetMatrix = writeMatrix;
							} else if ("delete".equals(operationName)) {
								targetMatrix = deleteMatrix;
							} else if ("list".equals(operationName)
									|| "create".equals(operationName)
									|| "menu".equals(operationName)) {
								// Nada
							} else {
								logger.fatal("Invalid Operation: "
										+ operationName);
								throw new SecConfigException(
										"Invalid Operation: " + operationName);
							}

							if (targetMatrix != null) {
								// Es una operacion del tipo: {delete, write,
								// read}
								admin = getVisibility(operation, "admin");
								super1 = getVisibility(operation, "super");
								staff = getVisibility(operation, "staff");
								user = getVisibility(operation, "user");
								cli = getVisibility(operation, "cli");
								//TODO Configurar la visibilidad del nuevo rol
								projectManager = getVisibility(operation, "projectManager");

								Class _class = Class.forName(classname);
								Class<? extends ITransferObject> t = _class;
								//TODO Inlcuir la matriz con el nuevo rol
								putInMatrix(targetMatrix, t, admin, super1,
										staff, user, cli,projectManager);
							} else {
								// Es una operacion del tipo: {list, create,
								// menu}
								Class parameter = Class.forName(classname);
								boolean[] permision = this
										.getPermisionArray(operation);
								GrantedAuthority methodResult = null;

								if ("list".equals(operationName)) {
									methodResult = Permission
											.Entity_List(parameter);
								} else if ("create".equals(operationName)) {
									methodResult = Permission
											.Entity_Create(parameter);
								} else if ("menu".equals(operationName)) {
									methodResult = Permission
											.Entity_Menu(parameter);
								}

								if (methodResult == null) {
									throw new NullPointerException(
											"Permission return null");
								}
								permissionsMap.put(methodResult, permision);
							}
						} else if ("detailView".equals(operation.getNodeName())) {
							NodeList fields = operation.getChildNodes();
							for (int k = 0, fCount = fields.getLength(); k < fCount; k++) {
								Node field = fields.item(k);
								if ("field".equals(field.getNodeName())) {
									String fieldName = DOMUtils.getAttribute(field, "name");
									Boolean adminView = getFieldVisibility(field, "admin");
									Boolean superView = getFieldVisibility(field, "super");
									Boolean staffView = getFieldVisibility(field, "staff");
									Boolean userView = getFieldVisibility(field, "user");
									Boolean cliView = getFieldVisibility(field, "cli");
									//TODO Añadir la nueva vista del projectManager
									Boolean projectManagerView = getFieldVisibility(field, "projectManager");

									Class _class = Class.forName(classname);
									Class<? extends ITransferObject> t = _class;
									//TODO añadir la vista del nuevo ROL
									putInDetailViewMatrix(t, fieldName, adminView, superView, staffView, userView,
											cliView, projectManagerView);
								}
							}
						}
					}
				}
			}
		} catch (SecConfigException ex) {
			throw ex;
		} catch (Exception ex) {
			logger.fatal(ex);
			throw new SecConfigException("Invalid entity detected: "
					+ classname, ex);
		}
	}

	public Map<AclMatrixKey, AclMatrixValue> getWriteMatrix() {
		return writeMatrix;
	}

	public Map<AclMatrixKey, AclMatrixValue> getReadMatrix() {
		return readMatrix;
	}

	public Map<AclMatrixKey, AclMatrixValue> getDeleteMatrix() {
		return deleteMatrix;
	}

	public Map<Integer, GrantedAuthority[]> getRolesMatrix() {
		return rolePermissions;
	}

	/**
	 * @param document
	 * @param permissionsMap
	 */
	private void loadActions(org.w3c.dom.Document document,
			Map<GrantedAuthority, boolean[]> permissionsMap)
			throws SecConfigException {
		Node action = null;
		String name = null;
		boolean[] permision = null;
		java.lang.reflect.Field _field = null;
		GrantedAuthority ga = null;

		try {
			NodeList actions = document.getElementsByTagName("action");
			for (int i = 0, iCount = actions.getLength(); i < iCount; i++) {
				action = actions.item(i);
				name = DOMUtils.getAttribute(action, "name");
				_field = Permission.class.getField(name);

				permision = getPermisionArray(action);
				ga = (GrantedAuthority) _field.get(null);
				permissionsMap.put(ga, permision);
			} // end for
		} catch (Exception ex) {
			logger.fatal(ex);

			throw new SecConfigException(null, ex);
		}
	}

	/**
	 * @param attrs
	 * @return
	 */
	private boolean[] getPermisionArray(Node node) {
		boolean[] privileges = new boolean[5];
		String[] profiles = { "admin", "super", "staff", "user", "cli" };
		String pvalue;

		for (int i = 0; i < profiles.length; i++) {
			pvalue = DOMUtils.getAttribute(node, profiles[i]);
			privileges[i] = "true".equals(pvalue);
		}
		return privileges;
	}

	/**
	 * Add levels to a matrix for a specified matrix entry
	 * 
	 * @param matrix
	 *            the matrix to add to
	 * @param type
	 *            type of transfer object
	 * @param adminLevel
	 *            level to assign to administrator users
	 * @param supervisorLevel
	 *            level to assign to supervisor users
	 * @param staffLevel
	 *            level to assign to staff users
	 * @param userLevel
	 *            level to assign to normal users
	 * @param clientLevel
	 *            level to assign to client users
	 */
	private void putInMatrix(Map<AclMatrixKey, AclMatrixValue> matrix,
			Class<? extends ITransferObject> type, AclMatrixValue adminLevel,
			AclMatrixValue supervisorLevel, AclMatrixValue staffLevel,
			AclMatrixValue userLevel, AclMatrixValue clientLevel, AclMatrixValue projectManagerLevel) {

		matrix.put(new AclMatrixKey(type, roleAdminId), adminLevel);
		matrix.put(new AclMatrixKey(type, roleSupervisorId), supervisorLevel);
		matrix.put(new AclMatrixKey(type, roleStaffId), staffLevel);
		matrix.put(new AclMatrixKey(type, roleUserId), userLevel);
		matrix.put(new AclMatrixKey(type, roleClientId), clientLevel);
		//TODO Añadir el nivel del nuevo ROL a la matriz
		matrix.put(new AclMatrixKey(type, roleProjectManagerId), projectManagerLevel);
	}
	
	/**
	 * Add levels to a matrix for a specified matrix entry
	 * 
	 * @param type type of transfer object
	 * @param fieldName Name of the field in the entity
	 * @param adminView If is true lets administrator user to view the field.
	 * @param supervisorView If is true lets supervisor user to view the field.
	 * @param staffView If is true lets staff user to view the field.
	 * @param userView If is true lets normal user to view the field.
	 * @param clientView If is true lets client user to view the field.
	 */
	private void putInDetailViewMatrix(Class<? extends ITransferObject> type, String fieldName, Boolean adminView,
			Boolean supervisorView, Boolean staffView, Boolean userView, Boolean clientView, Boolean projectManagerView) {

		this.detailViewMatrix.put(new FieldAclMatrixKey(type, roleAdminId, fieldName), adminView);
		this.detailViewMatrix.put(new FieldAclMatrixKey(type, roleSupervisorId, fieldName), supervisorView);
		this.detailViewMatrix.put(new FieldAclMatrixKey(type, roleStaffId, fieldName), staffView);
		this.detailViewMatrix.put(new FieldAclMatrixKey(type, roleUserId, fieldName), userView);
		this.detailViewMatrix.put(new FieldAclMatrixKey(type, roleClientId, fieldName), clientView);
		
		//TODO AÑADIR LA VISTA DEL NUEVO ROL
		this.detailViewMatrix.put(new FieldAclMatrixKey(type, roleProjectManagerId, fieldName), projectManagerView);
	}

	private GrantedAuthority[] loadPermsArray(
			Map<GrantedAuthority, boolean[]> permissionsMap, int index) {
		List<GrantedAuthority> grant = new ArrayList<GrantedAuthority>();
		Set<GrantedAuthority> perms = permissionsMap.keySet();
		for (GrantedAuthority perm : perms) {
			if (permissionsMap.get(perm)[index]) {
				grant.add(perm);
			}
		}
		return grant.toArray(new GrantedAuthority[] {});
	}

	/**
	 * @param node
	 *            Nodo 'operation'
	 * @param name
	 *            Identificador de la propiedad
	 * @return Realiza la conversión entre String a uno de los valores de la
	 *         enumeración AclMatrixValue
	 */
	private AclMatrixValue getVisibility(Node operation, String name) {
		String value = null;
		AclMatrixValue aclValue = null;

		value = DOMUtils.getAttribute(operation, name);
		if ("all".equals(value)) {
			aclValue = AclMatrixValue.ALL;
		} else if ("area".equals(value)) {
			aclValue = AclMatrixValue.AREA;
		} else if ("own".equals(value)) {
			aclValue = AclMatrixValue.OWN;
		} else if ("deny".equals(value)) {
			aclValue = AclMatrixValue.DENY;
		} else if ("owners".equals(value)) {
			aclValue = AclMatrixValue.OWNERS;
		} else {
			logger.warn("Invalid visibility data. name=" + name + ", value="
					+ value + ". Setting 'deny' value");
			aclValue = AclMatrixValue.DENY;
		}

		return aclValue;
	}
	
	/**
	 * @param node Nodo 'field'
	 * @param name Nombre del campo
	 * @return Realiza la conversión entre String a su valor booleano
	 */
	private Boolean getFieldVisibility(Node field, String name) {
		String value = null;

		value = DOMUtils.getAttribute(field, name);
		return Boolean.valueOf(value);
	}

	/**
	 * Inicializa las matrices de privilegios de operaciones de Lectura,
	 * Escritura y Borrado de cada una de las clases.
	 * 
	 * @param permissionsMap
	 */
	private void loadFlows(org.w3c.dom.Document document,
			Map<String, WorkFlow> workFlowMatrix) throws SecConfigException {
		NodeList entities = null;
		Node entity = null;
		String name = null;
		NodeList states = null;
		Node state = null;

		// Cargamos las entidades
		entities = document.getElementsByTagName("workflow");
		for (int i = 0, iCount = entities.getLength(); i < iCount; i++) {

			entity = entities.item(i);
			if ("workflow".equals(entity.getNodeName())) {
				final WorkFlow workFlow = new WorkFlow();

				name = DOMUtils.getAttribute(entity, "name");

				if (logger.isDebugEnabled()) {
					logger.debug("work readed: " + name);
				}

				workFlow.setName(name);
				workFlow.setStates(new ArrayList<State>());

				states = entity.getChildNodes();
				for (int j = 0, jCount = states.getLength(); j < jCount; j++) {
					state = states.item(j);

					// <state name="CREATED" admin="true" super="true"
					// staff="true" user="true" cli="true">
					if ("state".equals(state.getNodeName())) {

						final State st = loadState(state);

						workFlow.getStates().add(st);
					}
				}
				workFlowMatrix.put(name, workFlow);
			}
		}

	}

	/**
	 * @param node
	 * @return
	 * @throws SecConfigException
	 */
	private State loadState(Node node) throws SecConfigException {

		final State state = new State();
		final String name = DOMUtils.getAttribute(node, "name");

		if (logger.isDebugEnabled()) {
			logger.debug("State readed: " + name);
		}

		state.setName(name);
		state
				.setFields(new ArrayList<Field>());

		final String admin = DOMUtils.getAttribute(node, "admin");
		final String super1 = DOMUtils.getAttribute(node, "super");
		final String staff = DOMUtils.getAttribute(node, "staff");
		final String user = DOMUtils.getAttribute(node, "user");
		final String cli = DOMUtils.getAttribute(node, "cli");
		//TODO recuperar el permiso del nuevo ROL
		final String projectManager = DOMUtils.getAttribute(node, "projectManager");

		if (admin != null) {
			state.setPermAdmin(Boolean.valueOf(admin.toLowerCase()));
		}

		if (super1 != null) {
			state.setPermSuper(Boolean.valueOf(super1.toLowerCase()));
		}

		if (staff != null) {
			state.setPermStaff(Boolean.valueOf(staff.toLowerCase()));
		}

		if (user != null) {
			state.setPermUser(Boolean.valueOf(user.toLowerCase()));
		}

		if (cli != null) {
			state.setPermCli(Boolean.valueOf(cli.toLowerCase()));
		}
		
		//TODO permisos del nuevo ROL
		if (projectManager != null) {
			state.setPermProjectManager(Boolean.valueOf(projectManager.toLowerCase()));
		}

		final NodeList fieldList = node.getChildNodes();
		for (int i = 0, iCount = fieldList.getLength(); i < iCount; i++) {
			Node field = fieldList.item(i);

			if ("field".equals(field.getNodeName())) {

				final Field f = loadField(field);

				state.getFields().add(f);
			}
		}

		return state;
	}

	/**
	 * @param node
	 * @return
	 * @throws SecConfigException
	 */
	private Field loadField(Node node) throws SecConfigException {

		final Field field = new Field();
		final String xname = DOMUtils.getAttribute(node, "name");

		final String xadmin = DOMUtils.getAttribute(node, "admin");
		final String xsuper1 = DOMUtils.getAttribute(node, "super");
		final String xstaff = DOMUtils.getAttribute(node, "staff");
		final String xuser = DOMUtils.getAttribute(node, "user");
		final String xcli = DOMUtils.getAttribute(node, "cli");
		//TODO preparación del nuevo rol
		final String projectManager = DOMUtils.getAttribute(node, "projectManager");

		if (logger.isDebugEnabled()) {
			logger.debug("Field readed: " + xname);
		}

		field.setName(xname);

		
		if (xadmin != null) {
			field
					.setPermAdmin(com.autentia.tnt.manager.workflow.Field.Permission
							.valueOf(xadmin.toUpperCase()));
		}

		if (xsuper1 != null) {
			field
					.setPermSuper(com.autentia.tnt.manager.workflow.Field.Permission
							.valueOf(xsuper1.toUpperCase()));
		}

		if (xstaff != null) {
			field
					.setPermStaff(com.autentia.tnt.manager.workflow.Field.Permission
							.valueOf(xstaff.toUpperCase()));
		}

		if (xuser != null) {
			field
					.setPermUser(com.autentia.tnt.manager.workflow.Field.Permission
							.valueOf(xuser.toUpperCase()));
		}

		if (xcli != null) {
			field
					.setPermCli(com.autentia.tnt.manager.workflow.Field.Permission
							.valueOf(xcli.toUpperCase()));
		}
		
		//TODO preparamos permisos del nuevo rol
		if (projectManager != null) {
			field
					.setPermProjectManager(com.autentia.tnt.manager.workflow.Field.Permission
							.valueOf(projectManager.toUpperCase()));
		}

		return field;
	}

	public Map<String, WorkFlow> getWorkFlowMatrix() {
		return workFlowMatrix;
	}
	
	public boolean allowFieldAccess(Class<? extends ITransferObject> clazz, int roleId, String fieldName) {
		FieldAclMatrixKey fieldAclMatrixKey = new FieldAclMatrixKey(clazz, roleId, fieldName);
		Boolean ret = this.detailViewMatrix.get(fieldAclMatrixKey);
		if (ret == null) {
			ret = Boolean.FALSE;
		}
		return ret;
	}

	public boolean isUserInAdminRole(Principal principal) {
		
		return this.roleAdminId == principal.getRoleId();
	}
}
