package com.paulo.evento.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.paulo.evento.model.Convidado;
import com.paulo.evento.model.Evento;
import com.paulo.evento.repositorio.ConviadoRepository;
import com.paulo.evento.repositorio.EventoRepositoy;

import jakarta.validation.Valid;

@Controller
@RequestMapping("evento")
public class EventoController {

    @Autowired
    private EventoRepositoy er;
    @Autowired
    private ConviadoRepository cr;
    
    @GetMapping("/adicionar")
    public String form(Model model){
        model.addAttribute("evento", new Evento());
        return "index";
    }

    @PostMapping("/adicionar")
    public String salvar(@Valid Evento evento, BindingResult result, RedirectAttributes redirectAttributes){
        if(result.hasErrors()){
            redirectAttributes.addFlashAttribute("mensagem","Erro ao tentar salvar evento no banco");
            return "index";
        }
        er.save(evento);
        redirectAttributes.addFlashAttribute("mensagem","evento salvo com sucesso");
        return "redirect:/evento/adicionar";
    }

    @GetMapping("/listar")
    public String listar(Model model){
        model.addAttribute("evento", er.findAll());
        return "listarEvento";
    }

    @GetMapping("/listar/apagar/{id}")
    public String apagar(@PathVariable("id") long id){
        er.deleteById(id);
        return "redirect:/evento/listar";
    }

    @GetMapping("/detalhe/{id}")
    public String detalhe(@PathVariable("id") long id, Model model){
        model.addAttribute("evento", er.findById(id).get());
        model.addAttribute("convidado", new Convidado());
        Evento evento = er.getReferenceById(id);
        model.addAttribute("convidEvento", cr.findByEvento(evento));
        return "detalheEvento";
    }

    @PostMapping("/detalhe/{id}")
    public String adicionarConvidado(@PathVariable("id") long id,@Valid Convidado convidado,
    BindingResult result, RedirectAttributes redirectAttributes){
        if(result.hasErrors()){
            redirectAttributes.addFlashAttribute("mensagem","Erro ao tentar salvar convidado no banco");
            return "redirect:/evento/detalhe/{id}";
        }

        convidado.setEvento(er.getReferenceById(id));
        cr.save(convidado);
        return "redirect:/evento/listar";
    }

    @GetMapping("/listar/editar/{id}")
    public String editar(@PathVariable("id") long id, Model model){
        model.addAttribute("evento", er.findById(id).get());
        return "editar";
    }

    @PostMapping("/listar/editar/{id}")
    public String salvarAlterar(@PathVariable("id") long id,@Valid Evento evento,
    BindingResult result, RedirectAttributes redirectAttributes){
        if(result.hasErrors()){
            redirectAttributes.addFlashAttribute("mensagem","Erro ao tentar salvar evento no banco");
            return "editar";
        }
        evento.setId(id);
        er.save(evento);
        redirectAttributes.addFlashAttribute("mensagem","evento salvo com sucesso");
        return "redirect:/evento/listar";
        
    }
}
