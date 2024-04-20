package com.hungvu.webgym.service.impl;

import com.hungvu.webgym.model.*;
import com.hungvu.webgym.repository.AppointmentRepository;
import com.hungvu.webgym.repository.ClassificationPetRepository;
import com.hungvu.webgym.repository.PetCareRepository;
import com.hungvu.webgym.request.AppointmentRequest;
import com.hungvu.webgym.request.ClassificationPetRequest;
import com.hungvu.webgym.service.IAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class AppointmentServiceImpl implements IAppointmentService {

    @Autowired
    private AppointmentRepository bookingRepository;

    @Autowired
    private PetCareRepository petCareRepository;
    @Autowired
    private ClassificationPetRepository classificationPetRepository;

    @Override
    public Appointment addAppointment(AppointmentRequest request) {
        PetCare petCare = petCareRepository.findById(request.getPetServiceId())
                .orElseThrow(() -> new IllegalArgumentException("Not found service pet"));

        Appointment createAppointment = new Appointment();
        createAppointment.setCustomerName(request.getCustomerName());
        createAppointment.setAppointmentDate(new Date());
        createAppointment.setCustomerPhone(request.getCustomerPhone());
        createAppointment.setCustomerEmail(request.getCustomerEmail());
        createAppointment.setTotalAmount(request.getTotalAmount());
        createAppointment.setStatus(AppointmentStatus.CHUA_XAC_NHAN);

        List<ClassificationPetRequest> classificationRequests = request.getClassifications();
        List<ClassificationValue> classificationValues = new ArrayList<>();
        for (ClassificationPetRequest classificationRequest : classificationRequests) {
            String classificationName = classificationRequest.getClassificationName();
            List<String> value = classificationRequest.getValues();
            ClassificationPet classificationPet = petCare.getClassifications().stream()
                    .filter(classification -> classification.getClassificationName().equals(classificationName))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Classification not found: " + classificationName));
            ClassificationValue classificationValue = classificationPetRepository.findByClassificationPetAndValue(classificationPet, value)
                    .orElseThrow(() -> new IllegalArgumentException("Classification value not found: " + value));
            classificationValues.add(classificationValue);
        }
        createAppointment.setClassifications(classificationValues);

        createAppointment.setPetCare(petCare);

        bookingRepository.save(createAppointment);

        return createAppointment;
    }

    @Override
    public Appointment updateAppointment(Long appointmentId, AppointmentRequest request) {
        Appointment existingAppointment = bookingRepository.findById(appointmentId)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy đơn"));

        PetCare petCare = petCareRepository.findById(request.getPetServiceId())
                .orElseThrow(() -> new IllegalArgumentException("Not found service pet"));

        existingAppointment.setCustomerName(request.getCustomerName());
        existingAppointment.setCustomerPhone(request.getCustomerPhone());
        existingAppointment.setCustomerEmail(request.getCustomerEmail());
        existingAppointment.setTotalAmount(request.getTotalAmount());

        existingAppointment.setPetCare(petCare);

        bookingRepository.save(existingAppointment);
        return existingAppointment;
    }

    @Override
    public void deleteAppointment(Long appointmentId) {
        bookingRepository.deleteById(appointmentId);
    }

    @Override
    public List<Appointment> getAllAppointment() {
        return bookingRepository.findAll();
    }
}
