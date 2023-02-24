package com.example.demo.Service;


import com.example.demo.Entities.*;
import com.example.demo.Repositories.GrootoudersRep;
import com.example.demo.Repositories.KinderenRep;
import com.example.demo.Repositories.KleinkinderenRep;
import com.example.demo.Repositories.OudersRep;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@AllArgsConstructor
@org.springframework.stereotype.Service
@Component
public class Service {


    private final GrootoudersRep grootoudersRep;
    private final OudersRep oudersRep;
    private final KinderenRep kinderenRep;
    private final KleinkinderenRep kleinkinderenRep;


    public void kinderen_toevoegen(OuderKind ouderKind) {
        Integer oid = oudersRep.getOid(ouderKind.getOuder());
        Kinderen kind = new Kinderen(1, oid, ouderKind.getKind(), "");
        kinderenRep.save(kind);
    }

    public void kleinKinderen_toevoegen(KindKleinKind kindKleinKind) {
        Integer oid = kinderenRep.getOid(kindKleinKind.getOuder());
        Kleinkinderen kleinkind = new Kleinkinderen(1, oid, kindKleinKind.getKleinkind());
        kleinkinderenRep.save(kleinkind);
    }

    public List<Ouders> getAllOuders() {
        return (List<Ouders>) oudersRep.findAll();
    }

    public List<Kinderen> getAllKinderen() {
        return (List<Kinderen>) kinderenRep.findAll();
    }

    public List<Kleinkinderen> getAllkleinKinderen() {
        return (List<Kleinkinderen>) kleinkinderenRep.findAll();
    }

    public void familie_toevoegen(List<String> ouders, List<String> kinderen, List<String> kleinkinderen) {
        String hoofdouder = ouders.get(0);
        oudersRep.setvisibleTrue(hoofdouder);
        oudersRep.setAanhang(hoofdouder, ouders.get(1));
        System.out.println(kleinkinderen);
        SetKinderen(hoofdouder, kinderen, kleinkinderen, true);
    }

    public void familie_toevoegen_zonder_kinderen(List<String> ouders){
        String hoofdouder = ouders.get(0);
        oudersRep.setvisibleTrue(hoofdouder);
        oudersRep.setAanhang(hoofdouder, ouders.get(1));
    }

    public void familie_toevoegen_zonder_kleinkinderen(List<String> ouders, List<String> kinderen){
        String hoofdouder = ouders.get(0);
        oudersRep.setvisibleTrue(hoofdouder);
        oudersRep.setAanhang(hoofdouder, ouders.get(1));
        SetKinderen(ouders.get(0), kinderen, new ArrayList<>(), false);
    }

    public Integer SetKinderen(String hoofdouder, List<String> kinderen, List<String> kleinkinderen, boolean kleinkinderen_bool) {
        Integer oid = oudersRep.getOid(hoofdouder);
        Integer id = null;
        for (int i = 0; i < kinderen.size(); i += 3) {
            int bestaat = kinderenRep.checkofbestaan(kinderen.get(i + 1), kinderen.get(i + 2), oid);
            if (bestaat == 0 & kinderen.get(i+1).length() > 0) {
                kinderenRep.save(new Kinderen(1, oid, kinderen.get(i + 1), kinderen.get(i + 2)));
                id = kinderenRep.getId(kinderen.get(i + 1), kinderen.get(i + 2), oid);
            } else {
                id = kinderenRep.getId(kinderen.get(i + 1), kinderen.get(i + 2), oid);
                kinderenRep.Update_kinderen(kinderen.get(i + 1), kinderen.get(i + 2), id);
            }
            if (kleinkinderen_bool)
                SetKleinKinderen(kinderen, kleinkinderen, i, id);
        }
        return id;
    }

    public void SetKleinKinderen(List<String> kinderen, List<String> kleinkinderen, Integer i, Integer id) {
        int current_id = Integer.parseInt(kinderen.get(i));
        int index_of_start = IntStream.range(0, kleinkinderen.size()).filter(index -> kleinkinderen.get(index)
                .equals(String.valueOf(current_id))).findFirst().orElse(-1);
        int index_of_eind = IntStream.range(0, kleinkinderen.size()).filter(index -> kleinkinderen.get(index)
                .equals(String.valueOf(current_id + 1))).findFirst().orElse(-1);
        List<String> new_kk = new ArrayList<>();
        if (index_of_eind != -1) {
            new_kk = kleinkinderen.subList(index_of_start + 1, index_of_eind);
        } else {
            new_kk = kleinkinderen.subList(index_of_start + 1, kleinkinderen.size());
        }
        for (String kleinkindje : new_kk) {
            int bestaat2 = kleinkinderenRep.checkofbestaan(kleinkindje, id);
            if (bestaat2 == 0 & kleinkindje.length() > 0) {
                kleinkinderenRep.save(new Kleinkinderen(1, id, kleinkindje));
            } else {
                kleinkinderenRep.Update_kleinkinderen(kleinkindje, id);
            }
        }
    }
}
