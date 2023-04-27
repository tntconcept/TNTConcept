package com.autentia.tnt.converter;

import com.sun.faces.context.FacesContextImpl;
import org.junit.Test;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import javax.faces.convert.ConverterException;

import static com.autentia.tnt.converter.MinuteToHourConverter.ALLOW_DECIMAL;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class MinuteToHourConverterTest {

    private final FacesContext facesContext = new FacesContextImpl();
    private final UIComponent uiComponent = new UIOutput();

    private MinuteToHourConverter minuteToHourConverter = new MinuteToHourConverter(
            (messageId, params) -> new FacesMessage("summary", "detail")
    );

    @Test
    public void shouldGetAsObjectNoDecimals() {
        uiComponent.getAttributes().put(ALLOW_DECIMAL, "false");

        assertEquals(3600, minuteToHourConverter.getAsObject(facesContext, uiComponent, "60"));
        assertEquals(60, minuteToHourConverter.getAsObject(facesContext, uiComponent, "1"));
    }

    @Test
    public void shouldGetAsObjectDecimals() {
        uiComponent.getAttributes().put(ALLOW_DECIMAL, "true");

        assertEquals(3630, minuteToHourConverter.getAsObject(facesContext, uiComponent, "60.5"));
    }

    @Test
    public void shouldGetAsObjectFailInvalidInputValue() {
        uiComponent.getAttributes().put(ALLOW_DECIMAL, "true");

        assertThrows(ConverterException.class, () -> minuteToHourConverter.getAsObject(facesContext, uiComponent, "kk"));
    }

    @Test
    public void shouldGetAsStringDecimalValue() {
        uiComponent.getAttributes().put(ALLOW_DECIMAL, "true");

        assertEquals("60.5", minuteToHourConverter.getAsString(facesContext, uiComponent, 3630));
    }

    @Test
    public void shouldGetAsStringNoDecimalValue() {
        uiComponent.getAttributes().put(ALLOW_DECIMAL, "false");

        assertEquals("60", minuteToHourConverter.getAsString(facesContext, uiComponent, 3630));
        assertEquals("1", minuteToHourConverter.getAsString(facesContext, uiComponent, 60));
    }


    @Test
    public void shouldIntWhenInvalidAllowDecimal() {
        uiComponent.getAttributes().put(ALLOW_DECIMAL, "kk");

        assertEquals("60", minuteToHourConverter.getAsString(facesContext, uiComponent, 3630));
    }


}