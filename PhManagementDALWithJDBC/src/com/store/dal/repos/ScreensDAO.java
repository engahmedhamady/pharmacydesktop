
package com.store.dal.repos;

import com.store.dal.entities.Screens;
import com.store.dal.myGenerics.repos.commonDAO;
import java.util.List;

public class ScreensDAO implements  commonDAO<Screens>{
      @Override
      public Screens add(Screens screens) {
            
            return screens;

      }

      @Override
      public Screens update(Screens screens) {
            
            return   screens;

      }

      @Override
      public void remove(Object seq) {
           

      }

      @Override
      public Screens findById(Object seq) {
           return null;

      }

      public List<Screens> findList() {
            return null;
      }

    
      public void patchRemove(List<Screens> screenses) {
            if (screenses == null) {

                  return;
            }
            for (Screens screens : screenses) {
                  remove(screens.getSeq());
            }

      }
}
