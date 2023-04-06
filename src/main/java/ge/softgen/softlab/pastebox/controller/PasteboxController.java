package ge.softgen.softlab.pastebox.controller;

import ge.softgen.softlab.pastebox.api.request.PasteboxRequest;
import ge.softgen.softlab.pastebox.api.response.PasteboxResponse;
import ge.softgen.softlab.pastebox.api.response.PasteboxUrlResponse;
import ge.softgen.softlab.pastebox.service.PasteboxService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;

@RestController
@RequiredArgsConstructor
public class PasteboxController {
    private final PasteboxService pasteboxService;

    @GetMapping("/")
    public Collection<PasteboxResponse> getPublicPasteList() {
        return pasteboxService.getFirstPublicPasteboxes();
    }

    @GetMapping("/{hash}")
    public PasteboxResponse getByHash(@PathVariable String hash) {
        return pasteboxService.getByHash(hash);
    }

    @PostMapping("/")
    public PasteboxUrlResponse add(@RequestBody PasteboxRequest request) {

        return pasteboxService.create(request);
    }
}
