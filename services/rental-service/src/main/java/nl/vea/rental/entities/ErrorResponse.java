package nl.vea.rental.entities;

public record ErrorResponse(int statusCode, String statusName, String message) {
}
