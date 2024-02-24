public record PersonPutDTO(
        String name,
        String email,
        String password,
        LocalDate birthDate,
        String motherName,
        String fatherName,
        String telephone
) {}