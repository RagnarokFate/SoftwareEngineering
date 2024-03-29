package il.cshaifasweng.OCSFMediatorExample.entities;

//import com.sun.istack.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "ParkingSpot")
public class ParkingSpot  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int spotId_;


    int height =-1;
    int width =-1;
    int depth= -1;



    public LocalDateTime getExitDate() {
        return ExitDate;
    }

    public void setExitDate(LocalDateTime exitDate) {
        ExitDate = exitDate;
    }

    public LocalDateTime getEntryDate() {
        return EntryDate;
    }

    public void setEntryDate(LocalDateTime entryDate) {
        EntryDate = entryDate;
    }

    LocalDateTime ExitDate;
    LocalDateTime EntryDate;
    public String getCus_ID() {
        return Cus_ID;
    }

    public void setCus_ID(String cus_ID) {
        Cus_ID = cus_ID;
    }

    String Cus_ID;

    public String getLicesnes_Plate() {
        return Licesnes_Plate;
    }

    public void setLicesnes_Plate(String licesnes_Plate) {
        Licesnes_Plate = licesnes_Plate;
    }

    String Licesnes_Plate;

    String CurrentState;

    public boolean isOccasional() {
        return isOccasional;
    }

    public void setOccasional(boolean occasional) {
        isOccasional = occasional;
    }

    boolean isOccasional;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "id")
//    private ParkingLot parkingLot;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "parking_id")
    private ParkingLot parkingLot;

    int parking_lot_id ;

    public ParkingSpot(int a, int b, int c, String state, int thispark,ParkingLot parkingLot_)
    {
        height = a;
        width = b;
        depth = c;
        CurrentState = state;
        parking_lot_id  = thispark;
        parkingLot = parkingLot_;
        EntryDate = LocalDateTime.now();
        Licesnes_Plate = "";

    }

    public void resetEntryDate(){
        EntryDate = LocalDateTime.now();
    }


    public ParkingSpot() {

    }



    public void reset(){
        this.CurrentState = "empty";
        this.Licesnes_Plate = "";
        this.Cus_ID = "";
        this.isOccasional = false;
    }

    public int getSpotId_() {
        return spotId_;
    }

    public int getheight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public int getwidth() {
        return width;
    }


    public int getdepth() {
        return depth;
    }

    public void setdepth(int z) {
        this.depth = z;
    }

    public String getCurrentState() {
        return CurrentState;
    }

    public void setCurrentState(String currentState) {
        CurrentState = currentState;
    }

    public ParkingLot getParkingLot() {
        return parkingLot ;
    }

    public int getParking_lot_id(){
        return parking_lot_id;
    }

    public void setParkingLot(int parkingLot) {
        this.parking_lot_id  = parkingLot;
    }

    public String getLocation()
    {
        String Location = this.depth + "-" + this.height + "-" + this.width;
        return Location;
    }

    public void print() {
        System.out.println("the spot_id = " + getSpotId_());
        System.out.println("the state = " + getCurrentState());
        System.out.println("the cus id = " + getCus_ID());
        System.out.println("the car plate = " + getLicesnes_Plate());
    }
}
