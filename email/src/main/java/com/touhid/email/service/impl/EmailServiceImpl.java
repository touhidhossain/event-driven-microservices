package com.touhid.email.service.impl;

import com.touhid.email.domain.Email;
import com.touhid.email.repository.EmailRepository;
import com.touhid.email.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    private final EmailRepository emailRepository;
    @Override
    public Email save(Email email) {
        return emailRepository.save(email);
    }
}
