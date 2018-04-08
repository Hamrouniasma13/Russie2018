/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import java.util.List;
import entities.Offre;

/**
 *
 * @author asus
 */
public interface IOffreService {
     public void createOffre(Offre o);

    public List<Offre> getAll();

    public void updateOffre(Offre o);

    public void deleteOffre(int id);
}
