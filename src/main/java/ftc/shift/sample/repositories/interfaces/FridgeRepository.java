package ftc.shift.sample.repositories.interfaces;

import ftc.shift.sample.models.Fridge;

public interface FridgeRepository {

    Fridge fetchFridge(String id);

    Fridge updateFridge(Fridge fridge);

    void deleteFridge(String id);

    Fridge createFridge(Fridge fridge);
}