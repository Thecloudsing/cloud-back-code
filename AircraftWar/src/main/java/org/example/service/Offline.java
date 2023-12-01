package org.example.service;

import org.example.agreement.StreamingMedia;
import org.example.agreement.TypeProcessing;
import org.springframework.stereotype.Service;

@Service("Offline")
public class Offline implements TypeProcessing {
    @Override
    public void main(StreamingMedia streamingMedia) {

    }
}
