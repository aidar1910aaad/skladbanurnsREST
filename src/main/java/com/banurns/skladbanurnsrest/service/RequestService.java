package com.banurns.skladbanurnsrest.service;

import com.banurns.skladbanurnsrest.model.Request;

import java.util.List;

public interface RequestService {

    Request closeRequest(Long id);

    List<Request> getRequestList();

    Request addRequest(Request request , Long id);

    Request findById(Long id);

    List<Request> closeActive();

    void deleteRequestByID(Long id);



}
