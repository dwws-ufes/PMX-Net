package br.ifes.pmxnet.tool;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tools")
public class ToolDescriptionController {

    private final ToolDescriptionService service;

    public ToolDescriptionController(ToolDescriptionService service) {
        this.service = service;
    }

    @GetMapping("/{toolName}")
    public ToolDescriptionDTO getDescription(@PathVariable String toolName) throws Exception {
        String desc = service.getDescription(toolName);
        return new ToolDescriptionDTO(toolName, desc);
    }
}
