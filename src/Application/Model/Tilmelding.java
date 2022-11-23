package Application.Model;

public class Tilmelding {
    private double samletPris;
    private Deltager deltager;
    private Konference konference;
    private Hotel hotel;
    private Ledsager ledsager;

    public Tilmelding(double samletPris, Deltager deltager, Konference konference, Hotel hotel, Ledsager ledsager) {
        this.samletPris = samletPris;
        this.deltager = deltager;
        this.konference = konference;
        this.hotel = hotel;
        this.ledsager = ledsager;
    }
//----------------------------------------------------------------------------------------------------------------------
    // association --> 0..1 Hotel
    public Hotel getHotel() {
    return hotel;
}
    public void setHotel(Hotel hotel) {
        if (this.hotel != hotel) {
            Hotel oldHotel = this.hotel;
            if (oldHotel != null) {
                oldHotel.removeTilmelding(this);
            }
            this.hotel = hotel;
            if (hotel != null) {
                hotel.addTilmelding(this);
            }
        }
    }
}
//----------------------------------------------------------------------------------------------------------------------