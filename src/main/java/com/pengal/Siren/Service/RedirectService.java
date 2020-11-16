package com.pengal.Siren.Service;

import com.pengal.Siren.Entity.Redirect;
import com.pengal.Siren.Exception.BadRequestException;
import com.pengal.Siren.Repository.RedirectRepository;
import com.pengal.Siren.Request.RedirectCreationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RedirectService {
    private RedirectRepository redirectRepository;

    @Autowired
    public RedirectService(RedirectRepository redirectRepository) {
        this.redirectRepository = redirectRepository;
    }

    public Optional<Redirect> createRedirect(RedirectCreationRequest redirectCreationRequest) throws BadRequestException {
        if (redirectRepository.existsByAlias(redirectCreationRequest.getAlias())) {
            throw new BadRequestException("Alias already exits");
        }
        System.out.println("Redirect request" +  redirectCreationRequest.toString());
        Redirect redirect = redirectRepository.save(new Redirect(redirectCreationRequest.getAlias(), redirectCreationRequest.getUrl()));
        System.out.println("Redirect " +  redirect.toString());
        return Optional.ofNullable(redirect);
    }

    public Optional<Redirect> getRedirect() {
        return Optional.empty();
    }
}
