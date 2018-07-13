package ftc.shift.sample.repositories.classes;


import ftc.shift.sample.entity.FridgeEntity;
import ftc.shift.sample.repositories.interfaces.DataBaseInterfaces.FridgeRepository;
import ftc.shift.sample.repositories.interfaces.EntityUnterfaces.FridgeRepositoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;

@Repository
public class PostgresFridgeRepository  implements FridgeRepository {

    @Autowired
    private FridgeRepositoryEntity fridgeRepository;

    @Override
    public ArrayList<FridgeEntity> fetchUserFridge(Integer idUser) {
        Iterable<FridgeEntity> fridges= fridgeRepository.findAll();
        ArrayList<FridgeEntity> result=new ArrayList<>();
        for(FridgeEntity fridgeEntity: fridges){
            if(fridgeEntity.getUserId().equals(idUser)) {
                result.add(fridgeEntity);
            }
        }
        return result;
    }

    @Override
    public void addProductInUserFridge(FridgeEntity fridgeEntity) {
        fridgeRepository.save(fridgeEntity);
    }

    @Override
    public void removeProductFromUserFridge(Integer idUser, Integer idProduct) {
        Iterable<FridgeEntity> fridges= fridgeRepository.findAll();
        for (FridgeEntity fridgeEntity:fridges){
            if((fridgeEntity.getUserId().equals(idUser))
                    &&(fridgeEntity.getProductId().equals(idProduct))){
                fridgeRepository.delete(fridgeEntity.getId());
                return;
            }

        }
    }

}
