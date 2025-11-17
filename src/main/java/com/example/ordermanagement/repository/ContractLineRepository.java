//package com.example.ordermanagement.repository;
//
//import com.example.ordermanagement.model.ContractLine;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public class ContractLineRepository extends InFileRepository<ContractLine> {
//    public ContractLineRepository() {
//        super("src/main/resources/data/contractLine.json",  ContractLine.class);
//    }
//}


package com.example.ordermanagement.repository;

import com.example.ordermanagement.model.ContractLine;
import com.example.ordermanagement.model.SellableItem;
import com.example.ordermanagement.model.UnitsOfMeasure;
import com.example.ordermanagement.service.SellableItemService;
import com.example.ordermanagement.service.UnitsOfMeasureService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ContractLineRepository extends InFileRepository<ContractLine> {

    private final SellableItemService sellableItemService;
    private final UnitsOfMeasureService unitsOfMeasureService;
    private final ObjectMapper objectMapper;

    public ContractLineRepository(SellableItemService sellableItemService, UnitsOfMeasureService unitsOfMeasureService) {
        super("src/main/resources/data/contractLine.json", ContractLine.class);
        this.sellableItemService = sellableItemService;
        this.unitsOfMeasureService = unitsOfMeasureService;
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public List<ContractLine> findAll() {
        List<ContractLine> result = new ArrayList<>();
        try {
            // Citim JSON-ul ca o listă de obiecte intermediare cu String-uri pentru item/unit
            List<ContractLineJsonDTO> rawList = objectMapper.readValue(
                    new File("src/main/resources/data/contractLine.json"),
                    new TypeReference<List<ContractLineJsonDTO>>() {}
            );

            // Pentru fiecare obiect convertim id-urile în obiecte reale
            for (ContractLineJsonDTO dto : rawList) {
                SellableItem item = sellableItemService.getById(dto.getItem());
                UnitsOfMeasure unit = unitsOfMeasureService.getById(dto.getUnit());
                double quantity = Double.parseDouble(dto.getQuantity());

                ContractLine cl = new ContractLine(dto.getId(), item, unit, quantity);
                result.add(cl);
            }

        } catch (Exception e) {
            e.printStackTrace();
            // aici gestionezi eventual erorile (logging, exceptii, etc)
        }
        return result;
    }

    // Clasa DTO intermediară folosită doar la citirea JSON-ului
    private static class ContractLineJsonDTO {
        private String id;
        private String item;
        private String unit;
        private String quantity;

        // Getters & Setters (generate)

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getItem() {
            return item;
        }

        public void setItem(String item) {
            this.item = item;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public String getQuantity() {
            return quantity;
        }

        public void setQuantity(String quantity) {
            this.quantity = quantity;
        }
    }
}
