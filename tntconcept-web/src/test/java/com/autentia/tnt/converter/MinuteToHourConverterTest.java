package com.autentia.tnt.converter;

import com.sun.faces.context.FacesContextImpl;
import org.junit.Test;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import javax.faces.convert.ConverterException;

import java.util.Map;

import static com.autentia.tnt.converter.MinuteToHourConverter.ALLOW_DECIMAL;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MinuteToHourConverterTest {

    private final FacesContext facesContext = mock(FacesContextImpl.class);
    private final UIComponent uiComponent = mock(UIOutput.class);

    private final MinuteToHourConverter.MinuteToHourConverterMessageHandler minuteToHourConverterMessageHandler = mock(MinuteToHourConverter.MinuteToHourConverterMessageHandler.class);

    private MinuteToHourConverter minuteToHourConverter = new MinuteToHourConverter(minuteToHourConverterMessageHandler);
    private static final String REQUIRED_MESSAGE_ID = "javax.faces.component.UIInput.REQUIRED";
    private static final String CONVERSION_MESSAGE_ID = "javax.faces.component.UIInput.CONVERSION";

    @Test
    public void shouldGetAsObjectNoDecimals() {
        when(uiComponent.getAttributes().get(ALLOW_DECIMAL)).thenReturn(Map.of(ALLOW_DECIMAL, "false"));
        assertEquals(3600, minuteToHourConverter.getAsObject(facesContext, uiComponent, "60"));
        assertEquals(60, minuteToHourConverter.getAsObject(facesContext, uiComponent, "1"));
    }

    @Test
    public void shouldGetAsObjectDecimals() {
        when(uiComponent.getAttributes().get(ALLOW_DECIMAL)).thenReturn(Map.of(ALLOW_DECIMAL, "true"));
        assertEquals(3630, minuteToHourConverter.getAsObject(facesContext, uiComponent, "60.5"));
    }

    @Test
    public void shouldGetAsObjectFailInvalidInputValue() {
        when(uiComponent.getAttributes().get(ALLOW_DECIMAL)).thenReturn(Map.of(ALLOW_DECIMAL, "true"));
        when(uiComponent.getId()).thenReturn("id");
        Object[] objects ={"id", "kk"};
        when(minuteToHourConverterMessageHandler.getMessage(CONVERSION_MESSAGE_ID, objects))
                .thenReturn(new FacesMessage(CONVERSION_MESSAGE_ID, "kk"));
        assertThrows(ConverterException.class, () -> minuteToHourConverter.getAsObject(facesContext, uiComponent, "kk"));
    }
    @Test
    public void shouldGetAsObjectFailEmptyInputValue() {
        when(uiComponent.getAttributes().get(ALLOW_DECIMAL)).thenReturn(Map.of(ALLOW_DECIMAL, "true"));
        when(uiComponent.getId()).thenReturn("id");
        Object[] objects ={"id", ""};
        when(minuteToHourConverterMessageHandler.getMessage(REQUIRED_MESSAGE_ID, objects))
                .thenReturn(new FacesMessage(REQUIRED_MESSAGE_ID, ""));
        assertThrows(ConverterException.class, () -> minuteToHourConverter.getAsObject(facesContext, uiComponent, ""));
    }

    @Test
    public void shouldGetAsStringDecimalValue() {
        when(uiComponent.getAttributes().get(ALLOW_DECIMAL)).thenReturn(Map.of(ALLOW_DECIMAL, "true"));

        assertEquals("60.5", minuteToHourConverter.getAsString(facesContext, uiComponent, 3630));
    }

    @Test
    public void shouldGetAsStringNoDecimalValue() {
        when(uiComponent.getAttributes().get(ALLOW_DECIMAL)).thenReturn(Map.of(ALLOW_DECIMAL, "false"));

        assertEquals("60", minuteToHourConverter.getAsString(facesContext, uiComponent, 3630));
        assertEquals("1", minuteToHourConverter.getAsString(facesContext, uiComponent, 60));
    }


    @Test
    public void shouldIntWhenInvalidAllowDecimal() {
        when(uiComponent.getAttributes().get(ALLOW_DECIMAL)).thenReturn(Map.of(ALLOW_DECIMAL, "kk"));

        assertEquals("60", minuteToHourConverter.getAsString(facesContext, uiComponent, 3630));
    }


}