package com.example.ordermanagement.editor;

import java.beans.PropertyEditorSupport;

import com.example.ordermanagement.model.UnitsOfMeasure;
import com.example.ordermanagement.service.UnitsOfMeasureService;

public class UnitEditor extends PropertyEditorSupport {

    private final UnitsOfMeasureService unitsOfMeasureService;

    public UnitEditor(UnitsOfMeasureService unitsOfMeasureService) {
        this.unitsOfMeasureService = unitsOfMeasureService;
    }

    @Override
    public void setAsText(String id) {
        if (id == null || id.isEmpty()) {
            setValue(null);
        } else {
            UnitsOfMeasure unit = unitsOfMeasureService.getById(Long.parseLong(id));
            setValue(unit);
        }
    }
}
