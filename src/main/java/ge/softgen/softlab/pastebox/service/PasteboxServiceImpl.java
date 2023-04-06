package ge.softgen.softlab.pastebox.service;

import ge.softgen.softlab.pastebox.api.request.PasteboxRequest;
import ge.softgen.softlab.pastebox.api.request.PublicStatus;
import ge.softgen.softlab.pastebox.api.response.PasteboxResponse;
import ge.softgen.softlab.pastebox.api.response.PasteboxUrlResponse;
import ge.softgen.softlab.pastebox.entity.PasteBoxEntity;
import ge.softgen.softlab.pastebox.repository.PasteboxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "app")
public class PasteboxServiceImpl implements PasteboxService {

    private String host = "http://abc.ru";
    private int publicListSize = 10;
    private int initialValue = 0;
    private final PasteboxRepository repository;
    private AtomicInteger idGenerator = new AtomicInteger(initialValue);

    @Override
    public PasteboxResponse getByHash(String hash) {
        PasteBoxEntity pasteBoxEntity = repository.getByHash(hash);
        return new PasteboxResponse(pasteBoxEntity.getData(), pasteBoxEntity.isPublic());
    }

    @Override
    public List<PasteboxResponse> getFirstPublicPasteboxes() {

        List<PasteBoxEntity> list = repository.getListOfPublicAndAlive(publicListSize);

        return list.stream().map(pasteBoxEntity ->
                new PasteboxResponse(pasteBoxEntity.getData(), pasteBoxEntity.isPublic()))
                        .collect(Collectors.toList());
    }

    @Override
    public PasteboxUrlResponse create(PasteboxRequest request) {

        int hash = generateId();
        PasteBoxEntity pasteBoxEntity = new PasteBoxEntity();
        pasteBoxEntity.setData(request.getData());
        pasteBoxEntity.setId(hash);
        pasteBoxEntity.setHash(Integer.toHexString(hash));
        pasteBoxEntity.setPublic(request.getPublicStatus() == PublicStatus.PUBLIC);
        pasteBoxEntity.setLifetime(LocalDateTime.now().plusSeconds(request.getExpirationTimeSeconds()));
             repository.add(pasteBoxEntity);

             return new PasteboxUrlResponse(host + "/" + pasteBoxEntity.getHash());

    }

    private int generateId() {
        return idGenerator.getAndIncrement();
    }
}
