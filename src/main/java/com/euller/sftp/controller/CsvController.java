package com.euller.sftp.controller;

import com.euller.sftp.service.csv.CsvService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class CsvController {

    private final CsvService csvService;

    @GetMapping("/manipulateCSV/{pci}/{pcf}")
    public void manipulateCSV(@PathVariable("pci") int pci, @PathVariable("pcf") int pcf)  {

        csvService.manipulateCSV(pci, pcf);

    }
}
