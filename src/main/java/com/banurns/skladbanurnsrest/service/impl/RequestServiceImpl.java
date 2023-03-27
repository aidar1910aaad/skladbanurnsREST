package com.banurns.skladbanurnsrest.service.impl;

import com.banurns.skladbanurnsrest.model.Request;
import com.banurns.skladbanurnsrest.model.Status;
import com.banurns.skladbanurnsrest.repository.RequestRepository;
import com.banurns.skladbanurnsrest.service.RequestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
public class RequestServiceImpl implements RequestService {

    private final RequestRepository requestRepository;
    @Autowired
    public RequestServiceImpl(RequestRepository requestRepository){
        this.requestRepository = requestRepository;
    }
    @Override
    public Request closeRequest(Long id) {
        Request req = requestRepository.findById(id).orElse(null);
        if (req == null){
            log.warn("Request with id " + id + " not found");
            return null;
        }
        req.setStatus(Status.NOT_ACTIVE);
        Request updateRequest = requestRepository.save(req);
        return updateRequest;
    }

    @Override
    public List<Request> getRequestList() {
        List<Request> requests = requestRepository.findAll();
        log.info("Request list");
        return requests;
    }

    @Override
    public Request addRequest(Request request , Long id) {
        request.setStatus(Status.ACTIVE);
        request.setCreationuserid(id);
        Request savedRequest = requestRepository.save(request);
        return savedRequest;
    }

    @Override
    public Request findById(Long id) {
        Request result = requestRepository.findById(id).orElse(null);
        if (result == null) {
            log.warn("request not found");
            return null;
        }
        log.info("Request findById " + id +  " =" + result);
        return result;
    }

    @Override
    public List<Request> closeActive(){
        List<Request> requestsActive = requestRepository.findAllByStatus(Status.ACTIVE);
        for(Request request : requestsActive){
            request.setStatus(Status.NOT_ACTIVE);
        }

        List<Request> savedRequests = requestRepository.saveAll(requestsActive);
        return savedRequests;
    }

    @Override
    public void deleteRequestByID(Long id) {
        requestRepository.deleteById(id);
        log.info("Request" + id + " deleted");
    }
}
