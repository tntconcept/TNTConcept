/**
 * TNTConcept Easy Enterprise Management by Autentia Real Bussiness Solution S.L.
 * Copyright (C) 2007 Autentia Real Bussiness Solution S.L.
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.autentia.tnt.converter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.myfaces.shared_tomahawk.util.MessageUtils;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

/**
 * JSF converter to change minutes into dates
 *
 * @author ivan
 */
public class MinuteToHourConverter implements Converter {
    private static final String REQUIRED_MESSAGE_ID = "javax.faces.component.UIInput.REQUIRED";
    private static final String CONVERSION_MESSAGE_ID = "javax.faces.component.UIInput.CONVERSION";
    public static final String ALLOW_DECIMAL = "allowDecimal";

    private static Log log = LogFactory.getLog(MinuteToHourConverter.class);

    private final MinuteToHourConverterMessageHandler minuteToHourConverterMessageHandler;

    public MinuteToHourConverter() {
        this(new MinuteToHourConverterMessageHandlerImpl());
    }
    // This constructor is used for testing purposes
    MinuteToHourConverter(MinuteToHourConverterMessageHandler minuteToHourConverterMessageHandler) {
        this.minuteToHourConverterMessageHandler = minuteToHourConverterMessageHandler;
    }

    public Object getAsObject(FacesContext context, UIComponent component, String value) throws ConverterException {
        log.debug("getAsObject - value=" + value);


        if (value == null) {
            return null;
        }

        boolean allowDecimal = getAllowDecimal(component);
        if (!allowDecimal && isDecimalValue(value)) {
            throw new ConverterException(minuteToHourConverterMessageHandler.getMessage(CONVERSION_MESSAGE_ID, new Object[]{
                    component.getId(), value}));
        }


        try {
            return (int) (Double.parseDouble(value.replaceAll(",", ".")) * 60);
        } catch (NumberFormatException e) {
            final String messageId = "".equals(value) ? REQUIRED_MESSAGE_ID : CONVERSION_MESSAGE_ID;
            throw new ConverterException(minuteToHourConverterMessageHandler.getMessage(messageId, new Object[]{
                    component.getId(), value}), e);

        }
    }

    private boolean isDecimalValue(String value) {
        return value.contains(".") || value.contains(",");
    }

    private static boolean getAllowDecimal(UIComponent component) {
        return Boolean.parseBoolean(component.getAttributes().get(ALLOW_DECIMAL) == null ? "true"
                : component.getAttributes().get(ALLOW_DECIMAL).toString());
    }

    public String getAsString(FacesContext context, UIComponent component, Object value) throws ConverterException {
        boolean allowDecimal = getAllowDecimal(component);
        if (value instanceof Integer) {
            int val = (Integer) value;
            if (!allowDecimal) {
                return Integer.toString(val / 60);
            } else {
                return Double.toString(val / 60.0);
            }
        } else {
            return (String) value;
        }
    }

    public interface MinuteToHourConverterMessageHandler {
        FacesMessage getMessage(String messageId, Object[] params);
    }

    public static class MinuteToHourConverterMessageHandlerImpl implements MinuteToHourConverterMessageHandler {

        @Override
        public FacesMessage getMessage(String messageId, Object[] params) {
            return MessageUtils.getMessage(messageId, params);
        }
    }

}