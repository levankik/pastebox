package ge.softgen.softlab.pastebox.service;

import ge.softgen.softlab.pastebox.api.request.PasteboxRequest;
import ge.softgen.softlab.pastebox.api.response.PasteboxResponse;
import ge.softgen.softlab.pastebox.api.response.PasteboxUrlResponse;

import java.util.List;

public interface PasteboxService {
    PasteboxResponse getByHash(String hash);
    List<PasteboxResponse> getFirstPublicPasteboxes();
    PasteboxUrlResponse create(PasteboxRequest request);
}
