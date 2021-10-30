package seedu.address.model.facility;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CAPACITY_COURT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FACILITY_NAME_COURT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_COURT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME_COURT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalFacilities.FIELD;
import static seedu.address.testutil.TypicalFacilities.KENT_RIDGE_SPORT_HALL_5_COURT_1;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.testutil.FacilityBuilder;
import seedu.address.testutil.PersonBuilder;

public class FacilityTest {

    @Test
    public void constructor_null_throwsException() {
        // null name
        assertThrows(NullPointerException.class, () -> new Facility(null, new Location("loc"),
                new Time("1111"), new Capacity("5")));
        // null location
        assertThrows(NullPointerException.class, () -> new Facility(new FacilityName("Court"), null,
                new Time("1111"), new Capacity("5")));
        // null time
        assertThrows(NullPointerException.class, () -> new Facility(new FacilityName("Court"), new Location("loc"),
                null, new Capacity("5")));
        // null capacity
        assertThrows(NullPointerException.class, () -> new Facility(new FacilityName("Court"), new Location("loc"),
                new Time("1111"), null));
    }

    @Test
    public void getPersonAsString_success() {
        Person person = new PersonBuilder().build();
        Person secondPerson = new PersonBuilder().withName("Matt").build();
        Facility facility = new FacilityBuilder().build();
        facility.addPersonToFacility(person);
        facility.addPersonToFacility(secondPerson);
        assertEquals("Amy Bee, Matt", facility.getPersonsAsString());
    }

    @Test
    public void clearAllocationList_emptiesList() {
        Person person = new PersonBuilder().build();
        Person secondPerson = new PersonBuilder().withName("Matt").build();
        Facility facility = new FacilityBuilder().build();
        facility.addPersonToFacility(person);
        facility.addPersonToFacility(secondPerson);
        facility.clearAllocationList();
        Facility expectedFacility = new FacilityBuilder().build();
        assertEquals(expectedFacility, facility);
    }

    @Test
    public void isSameFacility() {
        // same object -> returns true
        assertTrue(FIELD.isSameFacility(FIELD));

        // null -> returns false
        assertFalse(FIELD.isSameFacility(null));

        // same name, same location, all other attributes different -> returns true
        Facility editedField = new FacilityBuilder(FIELD).withTime(VALID_TIME_COURT).withCapacity(VALID_CAPACITY_COURT)
               .build();
        assertTrue(FIELD.isSameFacility(editedField));

        // different name, all other attributes same -> returns false
        editedField = new FacilityBuilder(FIELD).withFacilityName(VALID_FACILITY_NAME_COURT).build();
        assertFalse(FIELD.isSameFacility(editedField));

        // different location, all other attributes same -> returns false
        editedField = new FacilityBuilder(FIELD).withLocation(VALID_LOCATION_COURT).build();
        assertFalse(FIELD.isSameFacility(editedField));

        // name differs in case, all other attributes same -> returns false
        Facility editedCourt = new FacilityBuilder(KENT_RIDGE_SPORT_HALL_5_COURT_1)
                .withFacilityName(VALID_FACILITY_NAME_COURT.toLowerCase()).build();
        assertFalse(KENT_RIDGE_SPORT_HALL_5_COURT_1.isSameFacility(editedCourt));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_FACILITY_NAME_COURT + " ";
        editedCourt = new FacilityBuilder(KENT_RIDGE_SPORT_HALL_5_COURT_1)
                .withFacilityName(nameWithTrailingSpaces).build();
        assertFalse(KENT_RIDGE_SPORT_HALL_5_COURT_1.isSameFacility(editedCourt));

        // location differs in case, all other attributes same -> returns false
        editedCourt = new FacilityBuilder(KENT_RIDGE_SPORT_HALL_5_COURT_1)
                .withLocation(VALID_LOCATION_COURT.toLowerCase()).build();
        assertFalse(KENT_RIDGE_SPORT_HALL_5_COURT_1.isSameFacility(editedCourt));

        // location has trailing spaces, all other attributes same -> returns false
        String locationWithTrailingSpaces = VALID_LOCATION_COURT + " ";
        editedCourt = new FacilityBuilder(KENT_RIDGE_SPORT_HALL_5_COURT_1)
                .withLocation(locationWithTrailingSpaces).build();
        assertFalse(KENT_RIDGE_SPORT_HALL_5_COURT_1.isSameFacility(editedCourt));
    }

    @Test
    public void equals() {
        Facility facility = new Facility(new FacilityName("Court 1"), new Location("University Sports Hall"),
                new Time("1130"), new Capacity("10"));

        Facility facilityCopy = new Facility(new FacilityName("Court 1"), new Location("University Sports Hall"),
                new Time("1130"), new Capacity("10"));

        Facility differentFacility = new Facility(new FacilityName("Field"), new Location("Opp University Hall"),
                new Time("2045"), new Capacity("5"));

        assertTrue(facility.equals(facility));

        assertTrue(facility.equals(facilityCopy));

        assertFalse(facility.equals(null));

        assertFalse(facility.equals("5"));

        assertFalse(facility.equals(differentFacility));

        Facility differentName = new Facility(new FacilityName("Field"), new Location("University Sports Hall"),
                new Time("1130"), new Capacity("10"));
        assertFalse(facility.equals(differentName));

        Facility differentLocation = new Facility(new FacilityName("Court 1"), new Location("Somewhere"),
                new Time("1130"), new Capacity("10"));
        assertFalse(facility.equals(differentLocation));

        Facility differentTime = new Facility(new FacilityName("Court 1"), new Location("University Sports Hall"),
                new Time("2030"), new Capacity("10"));
        assertFalse(facility.equals(differentTime));

        Facility differentCapacity = new Facility(new FacilityName("Court 1"), new Location("University Sports Hall"),
                new Time("1130"), new Capacity("8"));
        assertFalse(facility.equals(differentCapacity));
    }
}
