package com.example.demo.Controller;

import com.example.demo.Entities.*;
import com.example.demo.Repositories.OudersRep;
import com.example.demo.Service.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class Controller {

    private final OudersRep oudersRep;
    private final Service service;

    @GetMapping("/")
    public String testje(){
        return "<h1>test<h1>";
    }

    @GetMapping("/getallouders")
    public Iterable<Ouders> getOuders(){
        return service.getAllOuders();
    }


    @GetMapping("/getallkinderen")
    public Iterable<Kinderen> getkinderen(){
        return service.getAllKinderen();
    }


    @GetMapping("/getallkleinkinderen")
    public Iterable<Kleinkinderen> getKleinkinderen(){
        return service.getAllkleinKinderen();
    }

    @PostMapping("/addKinderen")
    public void add_kinderen(@RequestBody OuderKind ouderKind){
        service.kinderen_toevoegen(ouderKind);
    }


    @PostMapping("/addKleinKinderen")
    public void add_kleinkinderen(@RequestBody KindKleinKind kindKleinKind){
        service.kleinKinderen_toevoegen(kindKleinKind);
    }

    @GetMapping("addFamilieLeden/{ouders}/{kinderen}/{kleinkinderen}")
    public void add_familie(@PathVariable("ouders") List<String> ouders, @PathVariable("kinderen") List<String> kinderen, @PathVariable("kleinkinderen") List<String> kleinkinderen){;
        service.familie_toevoegen(ouders,kinderen,kleinkinderen);
    }

    @GetMapping("addFamilieLeden/{ouders}")
    public void add_familie_zonder_kinderen(@PathVariable("ouders") List<String> ouders){
        service.familie_toevoegen_zonder_kinderen(ouders);
    }

    @GetMapping("addFamilieLeden/{ouders}/{kinderen}")
    public void add_familie(@PathVariable("ouders") List<String> ouders, @PathVariable("kinderen") List<String> kinderen){;
        service.familie_toevoegen_zonder_kleinkinderen(ouders,kinderen);
    }

}
