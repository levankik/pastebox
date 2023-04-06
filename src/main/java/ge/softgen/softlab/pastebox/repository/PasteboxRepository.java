package ge.softgen.softlab.pastebox.repository;

import ge.softgen.softlab.pastebox.entity.PasteBoxEntity;

import java.util.List;

public interface PasteboxRepository {
    PasteBoxEntity getByHash(String hash);
    List<PasteBoxEntity> getListOfPublicAndAlive(int amount);
    void add(PasteBoxEntity pasteBoxEntity);
}
