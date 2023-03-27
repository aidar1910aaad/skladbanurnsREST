package com.banurns.skladbanurnsrest.service;

import com.banurns.skladbanurnsrest.model.Miscellaneous;

import java.util.List;

public interface MiscellaneousService {

    Miscellaneous addMisc(Miscellaneous misc);

    List<Miscellaneous> getMiscs();

    List<Miscellaneous> getActiveMiscs();

    Miscellaneous EnableMiscById(Long id);

    Miscellaneous disableMiscById(Long id);

    void removeMiscById(Long id);

    Miscellaneous control(Long id , Long quantity);
}
