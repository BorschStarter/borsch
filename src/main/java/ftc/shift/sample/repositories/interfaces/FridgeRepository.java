package ftc.shift.sample.repositories.interfaces;

import ftc.shift.sample.models.Fridge;

import java.util.TreeMap;

public interface FridgeRepository {

    Fridge fetchFridge(String id);

    Fridge updateFridge(Fridge fridge);

    void deleteFridge(String id);

    Fridge createFridge(Fridge fridge);

    TreeMap<String,Fridge> getAllFridge();
}