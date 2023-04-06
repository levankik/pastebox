package ge.softgen.softlab.pastebox.api.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PasteboxResponse {
    private final String data;
    private final boolean status;
}
