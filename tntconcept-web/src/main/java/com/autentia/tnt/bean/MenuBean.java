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

package com.autentia.tnt.bean;

import java.io.Serializable;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Stack;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.validator.ValidatorException;

import org.acegisecurity.GrantedAuthority;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.myfaces.custom.tree2.HtmlTree;
import org.apache.myfaces.custom.tree2.TreeModel;
import org.apache.myfaces.custom.tree2.TreeModelBase;
import org.apache.myfaces.custom.tree2.TreeNode;
import org.apache.myfaces.custom.tree2.TreeNodeBase;

import com.autentia.tnt.businessobject.Account;
import com.autentia.tnt.businessobject.AccountEntry;
import com.autentia.tnt.businessobject.AccountEntryType;
import com.autentia.tnt.businessobject.Activity;
import com.autentia.tnt.businessobject.AdminHoliday;
import com.autentia.tnt.businessobject.Bill;
import com.autentia.tnt.businessobject.Book;
import com.autentia.tnt.businessobject.BulletinBoard;
import com.autentia.tnt.businessobject.BulletinBoardCategory;
import com.autentia.tnt.businessobject.CompanyState;
import com.autentia.tnt.businessobject.Contact;
import com.autentia.tnt.businessobject.ContractType;
import com.autentia.tnt.businessobject.Department;
import com.autentia.tnt.businessobject.FinancialRatio;
import com.autentia.tnt.businessobject.Holiday;
import com.autentia.tnt.businessobject.Idea;
import com.autentia.tnt.businessobject.Interaction;
import com.autentia.tnt.businessobject.InteractionType;
import com.autentia.tnt.businessobject.Inventary;
import com.autentia.tnt.businessobject.Magazine;
import com.autentia.tnt.businessobject.Objective;
import com.autentia.tnt.businessobject.Offer;
import com.autentia.tnt.businessobject.OfferRejectReason;
import com.autentia.tnt.businessobject.Organization;
import com.autentia.tnt.businessobject.OrganizationISOCategory;
import com.autentia.tnt.businessobject.OrganizationType;
import com.autentia.tnt.businessobject.PeriodicalAccountEntry;
import com.autentia.tnt.businessobject.Project;
import com.autentia.tnt.businessobject.Publication;
import com.autentia.tnt.businessobject.RequestHoliday;
import com.autentia.tnt.businessobject.Tutorial;
import com.autentia.tnt.businessobject.User;
import com.autentia.tnt.businessobject.UserCategory;
import com.autentia.tnt.manager.security.Permission;
import com.autentia.tnt.manager.security.Principal;
import com.autentia.tnt.util.FacesUtils;
import com.autentia.tnt.util.SpringUtils;

/**
 * Menu definition bean
 * @author ivan
 * @deprecated
 */
public class MenuBean extends BaseBean implements Serializable
{
  /** */
  private static final long serialVersionUID = 2871259772143172830L;
  
  private static final Log log = LogFactory.getLog(MenuBean.class);
  
  /** Language resources */
  private static final ResourceBundle msg = ResourceBundle.getBundle("com.autentia.tnt.resources.messages");
  
  /** Arbol */
  private HtmlTree _tree;
  
  private String _nodePath;
  
  /** Menu tree */
  private TreeNode menu;
  
  /**
   * Get menu tree
   * @return menu tree
   */
  public TreeNode getMenu()
  {
    // Create menu only the first time
    if( menu==null )
    {
      Principal creds = SpringUtils.getPrincipal();
      Stack<TreeNode> path = new Stack<TreeNode>();
      
      menu = new TreeNodeBase("menu", "Menu", false);
      path.push(menu);
      
      if( openNode( path, creds, null, "admin" ) )
      {
        addLeaf( path, creds, Permission.Entity_Menu(User.class),         "users" );        
        addLeaf( path, creds, Permission.Entity_Menu(UserCategory.class), "userCategorys" );
        addLeaf( path, creds, null,                                       "changePassword" );
        addLeaf( path, creds, Permission.Entity_Menu(Department.class),   "departments" );
      //  addLeaf( path, creds, Permission.Entity_Menu(Setting.class),      "settings" );
        closeNode( path );
      }
      
      if( openNode( path, creds, null, "masterTables" ) )
      {
        addLeaf( path, creds, Permission.Entity_Menu(AccountEntryType.class), "accountEntryTypes" );
        addLeaf( path, creds, Permission.Entity_Menu(OrganizationType.class), "organizationTypes" );
        addLeaf( path, creds, Permission.Entity_Menu(InteractionType.class), "interactionTypes" );
        addLeaf( path, creds, Permission.Entity_Menu(OrganizationISOCategory.class), "organizationISOCategorys" );
        addLeaf( path, creds, Permission.Entity_Menu(ContractType.class), "contractTypes" );
        addLeaf( path, creds, Permission.Entity_Menu(Magazine.class), "magazines" );
        addLeaf( path, creds, Permission.Entity_Menu(OfferRejectReason.class), "offerRejectReasons" );
        closeNode( path );
      }

      if( openNode( path, creds, null, "billing" ) )
      {
        addLeaf( path, creds, Permission.Entity_Menu(Bill.class), "bills" );
        addLeaf( path, creds, Permission.Entity_Menu(Account.class), "accounts" );
        addLeaf( path, creds, Permission.Entity_Menu(AccountEntry.class), "accountEntrys" );
        addLeaf( path, creds, Permission.Entity_Menu(PeriodicalAccountEntry.class), "periodicalAccountEntrys" );
        addLeaf( path, creds, Permission.Action_NOF, "nof" );
        addLeaf( path, creds, Permission.Entity_Menu(FinancialRatio.class), "financialRatios" );
        closeNode( path );
      }
      
      if( openNode( path, creds, null, "contacts" ) )
      {
    	addLeaf( path, creds, Permission.Entity_Menu(Organization.class), "organizations" );
    	addLeaf( path, creds, Permission.Entity_Menu(Interaction.class), "interactions" );
    	addLeaf( path, creds, Permission.Entity_Menu(Contact.class), "contacts" );        
        addLeaf( path, creds, Permission.Entity_Menu(Offer.class), "offers" );
        addLeaf( path, creds, Permission.Entity_Menu(Project.class),      "projects" );
        closeNode( path );
      }
      
      if( openNode( path, creds, null, "quality" ) )
      {
        addLeaf( path, creds, Permission.Action_ListQualityDocuments, "qualityDocuments" );
        closeNode( path );
      }
      
      if( openNode( path, creds, null, "bulletin") )
      {
        addLeaf( path, creds, Permission.Entity_Menu(BulletinBoard.class), "bulletinBoards" );
        addLeaf( path, creds, Permission.Entity_Menu(CompanyState.class), "companyStates" );
        addLeaf( path, creds, Permission.Entity_Menu(BulletinBoardCategory.class), "bulletinBoardCategorys" );
        addLeaf( path, creds, Permission.Entity_Menu(Idea.class), "ideas" );
        closeNode( path );
      }
      
      if( openNode( path, creds, null, "activity" ) )
      {
        addLeaf( path, creds, Permission.Entity_Menu(Activity.class), "activitys" );
        addLeaf( path, creds, Permission.Entity_Menu(Objective.class), "objectives" );
        closeNode( path );
      }
      
      if( openNode( path, creds, null, "reports" ) )
      {
    	addLeaf( path, creds, Permission.Action_GeneralReports, "generalReports" );
    	addLeaf( path, creds, Permission.Action_BitacoreReports, "bitacoreReports" );
    	addLeaf( path, creds, Permission.Action_BillReports, "billReports" );
    	addLeaf( path, creds, Permission.Action_ProjectReports, "projectReports" );
        addLeaf( path, creds, Permission.Action_InteractionReports, "interactionReports" );
        addLeaf( path, creds, Permission.Action_OrganizationReports, "organizationReports" );
        addLeaf( path, creds, Permission.Action_OfferReports, "offerReports" );
        addLeaf( path, creds, Permission.Action_OwnReports, "ownReports" );
        addLeaf( path, creds, Permission.Action_PersonalReports, "personalReports" );
        closeNode( path );
      }
      
      if( openNode( path, creds, null, "publish" ) )
      {
        addLeaf( path, creds, Permission.Entity_Menu(Tutorial.class), "tutorials" );
        addLeaf( path, creds, Permission.Entity_Menu(Publication.class), "publications" );
        closeNode( path );
      }
      
      if( openNode( path, creds, null, "holiday") )
      {
        addLeaf( path, creds, Permission.Entity_Menu(Holiday.class), "holidays" );
        addLeaf( path, creds, Permission.Entity_Menu(RequestHoliday.class), "requestHolidays");
        addLeaf( path, creds, Permission.Entity_Menu(AdminHoliday.class), "adminHolidays");
        closeNode( path );
      }
      
      if( openNode( path, creds, null, "utils") )
      {
        addLeaf( path, creds, Permission.Entity_Menu(Book.class), "books");
        addLeaf( path, creds, Permission.Entity_Menu(Inventary.class), "inventarys" );
        closeNode( path );
      }
    }
    
    return menu;
  }
  
  /**
   * Add non-leaf node to current node if it is accesible by current user
   * @param path path of current node
   * @param creds current user
   * @param neededRole role needed to render the node
   * @param parent parent node
   * @param cmd command name
   * @return true if node has been created
   */
  private boolean openNode( Stack<TreeNode> path, Principal creds, GrantedAuthority neededRole, String cmd )
  {
    boolean added = false;
    
    if( neededRole==null || creds.hasAuthority( neededRole ) )
    {
      String text;
      try
      {
        text = msg.getString("menu."+cmd);
      }
      catch( MissingResourceException e )
      {
        text = "MISSING : "+cmd+" : MISSING";
      }
      TreeNode child = new TreeNodeBase( "folder", text, cmd, false );
      path.peek().getChildren().add( child );
      path.push(child);
      added = true;
    }

    log.debug("openNode - "+(added?"OPEN  ":"IGNORE")+": "+cmd );
    return added;
  }

  /**
   * Add leaf node to parent node if it is accesible by current user
   * @param path path of current node
   * @param creds current user
   * @param neededRole role needed to render the node
   * @param parent parent node
   * @param cmd command name
   * @return true if node was added
   */
  private boolean addLeaf( Stack<TreeNode> path, Principal creds, GrantedAuthority neededRole, String cmd )
  {
    boolean added=false;
    
    if( neededRole==null || creds.hasAuthority( neededRole ) )
    {
      String text;
      try
      {
        text = msg.getString("menu."+cmd);
      }
      catch( MissingResourceException e )
      {
        text = "MISSING : "+cmd+" : MISSING";
      }
      TreeNode child = new TreeNodeBase( "document", text, cmd, true );
      path.peek().getChildren().add( child );
      added = true;
    }

    log.debug("addLeaf - "+(added?"ADD   ":"IGNORE")+": "+cmd );
    return added;
  }
  
  /**
   * Finalize an opened node and return its parent. If the given node has no 
   * childs it is removed from the tree.
   * @param path path of current node
   * @return false if the node was removed
   */
  private boolean closeNode( Stack<TreeNode> path )
  {
    boolean closed = true;
    
    TreeNode node = path.pop();
    if( node.getChildCount()==0 )
    {
      path.peek().getChildren().remove(node);
      closed = false;
    }
    
    log.debug("addLeaf - "+(closed?"CLOSE ":"REMOVE")+": "+node.getIdentifier() );
    return closed;
  }
  
  /**
   * NOTE: This is just to show an alternative way of supplying tree data.  You can supply either a
   * TreeModel or TreeNode.
   *
   * @return TreeModel
   */
  public TreeModel getExpandedTreeData()
  {
    return new TreeModelBase(getMenu());
  }
  
  public void setTree(HtmlTree tree)
  {
    _tree = tree;
  }
  
  public HtmlTree getTree()
  {
    return _tree;
  }
  
  public String expandAll()
  {
    _tree.expandAll();
    return null;
  }
  
  public void setNodePath(String nodePath)
  {
    _nodePath = nodePath;
  }
  
  public String getNodePath()
  {
    return _nodePath;
  }
  
  public void checkPath(FacesContext context, UIComponent component, java.lang.Object value)
  {
    // make sure path is valid (leaves cannot be expanded or renderer will complain)
    FacesMessage message = null;
    String[] path = _tree.getPathInformation(value.toString());
    
    for (int i = 0; i < path.length; i++)
    {
      String nodeId = path[i];
      try
      {
        _tree.setNodeId(nodeId);
      }
      catch (Exception e)
      {
        throw new ValidatorException(message, e);
      }
      
      if (_tree.getNode().isLeaf())
      {
        message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
            "Invalid node path (cannot expand a leaf): " + nodeId,
            "Invalid node path (cannot expand a leaf): " + nodeId);
        throw new ValidatorException(message);
      }
    }
  }
  
  public void expandPath(ActionEvent event)
  {
    _tree.expandPath(_tree.getPathInformation(_nodePath));
  }
  
  /**
   * Redirige a la función asociada al punto de menú seleccionado
   * @return
   */
  public String selectOption()
  {
    // Remove admin beans from session
    FacesUtils.removeFromSession("userBean");
    FacesUtils.removeFromSession("userCategoryBean");
    FacesUtils.removeFromSession("inventaryBean");
    FacesUtils.removeFromSession("projectBean");
    FacesUtils.removeFromSession("ideaBean");
    FacesUtils.removeFromSession("changePasswordBean");
    FacesUtils.removeFromSession("userBean");
    FacesUtils.removeFromSession("userCategoryBean");
    FacesUtils.removeFromSession("inventaryBean");
    FacesUtils.removeFromSession("projectBean");
    FacesUtils.removeFromSession("projectRoleBean");
    FacesUtils.removeFromSession("ideaBean");
    FacesUtils.removeFromSession("changePasswordBean");
    FacesUtils.removeFromSession("departmentBean");
    FacesUtils.removeFromSession("contractTypeBean");
    
    // Remove biling beans from session
    FacesUtils.removeFromSession("billBean");    
    FacesUtils.removeFromSession("accountBean");
    FacesUtils.removeFromSession("accountEntryTypeBean");
    FacesUtils.removeFromSession("accountEntryBean");
    FacesUtils.removeFromSession("periodicalAccountEntryBean");
    FacesUtils.removeFromSession("nofBean");
    FacesUtils.removeFromSession("financialRatioBean");
    
    // Remove contacts beans from session
    FacesUtils.removeFromSession("organizationBean");
    FacesUtils.removeFromSession("contactBean");
    FacesUtils.removeFromSession("interactionBean");
    FacesUtils.removeFromSession("interactionTypeBean");
    FacesUtils.removeFromSession("organizationTypeBean");
    FacesUtils.removeFromSession("organizationISOCategoryBean");
    FacesUtils.removeFromSession("offerRejectReasonBean");
    FacesUtils.removeFromSession("offerBean");
    
    // Remove quality beans from session
    FacesUtils.removeFromSession("qualityDocumentBean");
    
    // Remove bulletin beans from session
    FacesUtils.removeFromSession("bulletinBoardBean");
    FacesUtils.removeFromSession("companypathBean");
    FacesUtils.removeFromSession("bulletinBoardCategoryBean");
    
    // Remove activity beans from session
    FacesUtils.removeFromSession("activityBean");
    FacesUtils.removeFromSession("objectiveBean");
    
    // Remove activity beans from session
    FacesUtils.removeFromSession("activityReportBean");
    FacesUtils.removeFromSession("generalReportBean");
    FacesUtils.removeFromSession("billReportBean");
    FacesUtils.removeFromSession("interactionReportBean");
    FacesUtils.removeFromSession("projectReportBean");
    FacesUtils.removeFromSession("organizationReportBean");
    FacesUtils.removeFromSession("personalReportBean");
    FacesUtils.removeFromSession("offerReportBean");
    
    // Remove tutorial beans from session
    FacesUtils.removeFromSession("tutorialBean");
    FacesUtils.removeFromSession("publicationBean");
    FacesUtils.removeFromSession("magazineBean");
    
    // Remove util beans from session
    FacesUtils.removeFromSession("sendMailBean");
    
    FacesContext context = FacesContext.getCurrentInstance();
    java.util.Map map = context.getExternalContext().getRequestParameterMap();
    String path = (String) map.get("pathMenu");
    return path;
  }
}
