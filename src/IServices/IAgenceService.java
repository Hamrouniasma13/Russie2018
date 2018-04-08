/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import entities.Agence;
import java.util.List;

/**
 *
 * @author asus
 */
public interface IAgenceService {
    public void createAgence(Agence a);

    public List<Agence> getAll(String word);

    public void update(Agence a);

    public void delete(int id);
}
