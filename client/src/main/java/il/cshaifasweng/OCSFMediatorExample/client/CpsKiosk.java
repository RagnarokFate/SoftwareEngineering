/**
 * Sample Skeleton for 'cpsKiosk.fxml' Controller Class
 */

package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.PreOrder;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.util.Vector;

public class CpsKiosk {

    @FXML // fx:id="CustomerBtn"
    private Button CustomerBtn; // Value injected by FXMLLoader
    @FXML
    private Button complaintBTN;

    @FXML
    private Button ExitParkingBTN;

    @FXML // fx:id="EnterParkingBTN"
    private Button EnterParkingBTN; // Value injected by FXMLLoader

    @FXML // fx:id="ID_LOGIN_TF"
    private TextField ID_LOGIN_TF; // Value injected by FXMLLoader

    @FXML // fx:id="LICENSE_LOGIN_TF"
    private TextField LICENSE_LOGIN_TF; // Value injected by FXMLLoader

    @FXML // fx:id="ManagerBtn"
    private Button ManagerBtn; // Value injected by FXMLLoader

    @FXML // fx:id="PW_LOGIN_TF"
    private PasswordField PW_LOGIN_TF; // Value injected by FXMLLoader

    @FXML
    private Button backbtn;

    @FXML // fx:id="RenewSubsBtn"
    private Button RenewSubsBtn; // Value injected by FXMLLoader

    @FXML // fx:id="ReserveParkingBtn"
    private Button ReserveParkingBtn; // Value injected by FXMLLoader

    @FXML // fx:id="SUBSNUM_LOGIN_TF"
    private PasswordField SUBSNUM_LOGIN_TF; // Value injected by FXMLLoader

    @FXML // fx:id="WorkerBtn"
    private Button WorkerBtn; // Value injected by FXMLLoader

    @FXML // fx:id="checkReservBtn"
    private Button checkReservBtn; // Value injected by FXMLLoader

    @FXML // fx:id="createNewSubsBtn"
    private Button createNewSubsBtn; // Value injected by FXMLLoader

    @FXML
    private Label ClientErrorLabel;

    @FXML
    private Label EmployeeErrorLabel;

    String parkingLotName;

    @FXML
    void CustomerBtn(ActionEvent event) throws IOException {
        DataSingleton data = DataSingleton.getInstance();
        data.setData(parkingLotName);
        App.setRoot("ocasionalParking");
    }

    @FXML
    void ExitParkingBTN(ActionEvent event) throws IOException {
        Message msg= new Message("ExitParking");
        msg.setLicensePlate(LICENSE_LOGIN_TF.getText());
        msg.setSubNum(SUBSNUM_LOGIN_TF.getText());
        msg.setObject1(parkingLotName);
//        for(int i=0;i<LICENSE_LOGIN_TF.getLength();i++)
//        {
//            if(msg.getLicensePlate().charAt(i)<'0'||msg.getLicensePlate().charAt(i)>'9')
//            {
//                LICENSE_LOGIN_TF.setText("notValid");
//            }
//        }
//        for(int i=0;i<SUBSNUM_LOGIN_TF.getLength();i++)
//        {
//            if(msg.getSubNum().charAt(i)<'0'||msg.getSubNum().charAt(i)>'9')
//            {
//                SUBSNUM_LOGIN_TF.setText("notValid");
//            }
//        }
//
//
//        if(!LICENSE_LOGIN_TF.getText().equals("notValid")&&!SUBSNUM_LOGIN_TF.getText().equals("notValid"))
//        {
//
//        }
        SimpleClient.getClient().sendToServer(msg);
    }
    @FXML
    void EnterParkingBTN(ActionEvent event) throws IOException {
        Message msg= new Message("EnterParking4");
        msg.setLicensePlate(LICENSE_LOGIN_TF.getText());
        msg.setSubNum(SUBSNUM_LOGIN_TF.getText());
        msg.setObject1(parkingLotName);
        for(int i=0;i<LICENSE_LOGIN_TF.getLength();i++)
        {
            if(msg.getLicensePlate().charAt(i)<'0'||msg.getLicensePlate().charAt(i)>'9')
            {
                LICENSE_LOGIN_TF.setText("notValid");
            }
        }
        for(int i=0;i<SUBSNUM_LOGIN_TF.getLength();i++)
        {
            if(msg.getSubNum().charAt(i)<'0'||msg.getSubNum().charAt(i)>'9')
            {
                SUBSNUM_LOGIN_TF.setText("notValid");
            }
        }


        if(!LICENSE_LOGIN_TF.getText().equals("notValid")&&!SUBSNUM_LOGIN_TF.getText().equals("notValid"))
        {
            SimpleClient.getClient().sendToServer(msg);
        }

        // todo send alert
    }

    @FXML
    void ID_LOGIN_TF(ActionEvent event) {

    }

    @FXML
    void LICENSE_LOGIN_TF(KeyEvent event) {
        InputValidation test = new InputValidation();
        if(test.CarIDValidation(LICENSE_LOGIN_TF.getText().toString()))
        {
            ClientErrorLabel.setText("");

        }
        else
        {
            if(!LICENSE_LOGIN_TF.getText().toString().equals(""))
            {
                ClientErrorLabel.setTextFill(Color.web("#ff0000"));
                ClientErrorLabel.setText("Car ID is not valid, please try again!");
                LICENSE_LOGIN_TF.setText("");
            }
            else
            {
                ClientErrorLabel.setTextFill(Color.web("#ff0000"));
                ClientErrorLabel.setText("Car ID is empty, please fill it up!");


                LICENSE_LOGIN_TF.setText("");
            }
        }
    }

    @FXML
    void complaintBTNa(ActionEvent event) {
        DataSingleton data = DataSingleton.getInstance();
        data.setCaller("cpsKiosk");
        try {
            App.setRoot("ComplaintSubmittion");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void WorkerBtn(ActionEvent event) throws IOException {
        Message msg= new Message("loginEmployee");
        msg.setID(ID_LOGIN_TF.getText());
        msg.setPassword(PW_LOGIN_TF.getText());

//        for(int i=0;i<ID_LOGIN_TF.getLength();i++)
//        {
//            if(msg.getID().charAt(i)<'0'||msg.getID().charAt(i)>'9')
//            {
//                ID_LOGIN_TF.setText("notValid");
//            }
//        }
//        if(!ID_LOGIN_TF.getText().equals("notValid")) {
//
//        }
        SimpleClient.getClient().sendToServer(msg);
    }
    @Subscribe
    public void allowWorker(loginWorkerEvent allowing) throws IOException {

        int perm_lvl = 100000;
        System.out.println("Allow Worker");
        System.out.println(allowing.getMsg().getObject1());
        if(allowing.getMsg().getObject1() == "success") {
             perm_lvl = (Integer) allowing.getMsg().getObject3();
        }
        if(allowing.getMsg().getObject1().toString().equals("success") && perm_lvl == 2) {
            DataSingleton data = DataSingleton.getInstance();
            data.setDataName("ParkingWorker");
            il.cshaifasweng.OCSFMediatorExample.entities.ParkingWorker parkingWorker = (il.cshaifasweng.OCSFMediatorExample.entities.ParkingWorker) allowing.getMsg().getObject2();
            data.setData(parkingWorker.getUserID());

            data.setCaller("cpsKiosk");

            App.setRoot("EmployeeWindow");
        } else if(allowing.getMsg().getObject1().toString().equals("success") && perm_lvl == 3){
            DataSingleton data = DataSingleton.getInstance();
            data.setDataName("CustomerService");
            il.cshaifasweng.OCSFMediatorExample.entities.CustomerServiceWorker CustomerServiceWorkerz = (il.cshaifasweng.OCSFMediatorExample.entities.CustomerServiceWorker) allowing.getMsg().getObject2();
            data.setData(CustomerServiceWorkerz.getUserID());

            data.setCaller("cpsKiosk");

            App.setRoot("CustomerService");

        }else {
            ID_LOGIN_TF.setText("access denied");
            PW_LOGIN_TF.clear();

        }
    }

    @FXML
    void ManagerBtn(ActionEvent event) throws IOException {
        Message msg= new Message("loginManager_KIOSK");
        msg.setID(ID_LOGIN_TF.getText());
        msg.setPassword(PW_LOGIN_TF.getText());
            SimpleClient.getClient().sendToServer(msg);

    }
    @Subscribe
    public void EnterEvent(EnterParkingEvent event) throws IOException{
        System.out.println((String)event.getMessage().getObject1() );
        System.out.println("EnterEvent");
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION,
                    (String)event.getMessage().getObject1() );
            alert.show();
        });
    }
    @Subscribe
    public void allowManager(loginManagerKioskEvent allowing) throws IOException {
        System.out.println("Allow whech");

        int permission =(int) allowing.getMsg().getObject3();

        if(allowing.getMsg().getObject1().toString().equals("success") && permission ==  1) {

            DataSingleton data = DataSingleton.getInstance();
            data.setDataName("ParkingManager");
            il.cshaifasweng.OCSFMediatorExample.entities.ParkingManager parkingManager = (il.cshaifasweng.OCSFMediatorExample.entities.ParkingManager) allowing.getMsg().getObject2();
            data.setData(parkingManager.getid());
            data.setCaller("cpsKiosk");
            App.setRoot("ParkingManager");

            } else  if(allowing.getMsg().getObject1().toString().equals("success") && permission ==  0){

            DataSingleton data = DataSingleton.getInstance();
            data.setDataName("RegionalManager");
            il.cshaifasweng.OCSFMediatorExample.entities.RegionalManager regionalManager = (il.cshaifasweng.OCSFMediatorExample.entities.RegionalManager) allowing.getMsg().getObject2();
            data.setData(regionalManager.getUserID());
            data.setRegionalListOfRequests(allowing.getMsg().getObject4());
            data.setCaller("cpsKiosk");
            App.setRoot("RegionalManager");

        }else {
                  Platform.runLater(() -> {
                  Alert alert = new Alert(Alert.AlertType.INFORMATION,
                        "UserName or PassWord Wrong"
                );
                alert.show();
            });

        }

        if(allowing.getMsg().getMessage().equals("AllowEmployee")) {
            System.out.println("Allow Worker");
            System.out.println(allowing.getMsg().getObject1());

            if (allowing.getMsg().getObject1().equals("success")) {
                DataSingleton data = DataSingleton.getInstance();
                data.setDataName("ParkingWorker");
                il.cshaifasweng.OCSFMediatorExample.entities.ParkingWorker parkingWorker = (il.cshaifasweng.OCSFMediatorExample.entities.ParkingWorker) allowing.getMsg().getObject1();
                data.setData(parkingWorker.getUserID());

                data.setCaller("cpsKiosk");
                System.out.println("aere wsl set root ");
                App.setRoot("EmployeeWindow");
            } else {
                Platform.runLater(() -> {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION,
                            "UserName or PassWord Wrong"
                    );
                    alert.show();
                });

            }
        }
    }


    @FXML
    void PW_LOGIN_TF(ActionEvent event) {

    }




    @FXML
    void RenewSubsBtn(ActionEvent event) throws IOException {
        Message msg= new Message("RenewSub_kiosk");
        msg.setLicensePlate(LICENSE_LOGIN_TF.getText());
        msg.setSubNum(SUBSNUM_LOGIN_TF.getText());

        for(int i=0;i<LICENSE_LOGIN_TF.getLength();i++)
        {
            if(msg.getLicensePlate().charAt(i)<'0'||msg.getLicensePlate().charAt(i)>'9')
            {
                LICENSE_LOGIN_TF.setText("notValid");
            }
        }
        for(int i=0;i<SUBSNUM_LOGIN_TF.getLength();i++)
        {
            if(msg.getSubNum().charAt(i)<'0'||msg.getSubNum().charAt(i)>'9')
            {
                SUBSNUM_LOGIN_TF.setText("notValid");
            }
        }


        if(!LICENSE_LOGIN_TF.getText().equals("notValid")&&!SUBSNUM_LOGIN_TF.getText().equals("notValid"))
        {
            SimpleClient.getClient().sendToServer(msg);
        }
    }

    @Subscribe
   public void setRenewSubsSuccess(SubRenewEvent event)
    {
//            System.out.println("thbane");
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION,
                    "Message: Sub renewed"
            );
            alert.show();
        });
    }



    @FXML
    void ReserveParkingBtn(ActionEvent event) throws IOException {
        DataSingleton data = DataSingleton.getInstance();
        data.setCaller("cpsKiosk");
        App.setRoot("OneTimeParkingOrder");//todo check reservation window
    }

    @FXML
    void SUBSNUM_LOGIN_TF(ActionEvent event) {
    }

    @FXML
    void checkReservBtn(ActionEvent event) throws IOException {
        DataSingleton data = DataSingleton.getInstance();
        data.setCaller("cpsKiosk");
        App.setRoot("CheckReservation");//todo check reservation window
    }


    @FXML
    void createNewSubsBtn(ActionEvent event) throws IOException {
        DataSingleton data = DataSingleton.getInstance();
        data.setCaller("cpsKiosk");
     App.setRoot("RegisterNewSubscription");
    }

    @FXML
    void backbtn(ActionEvent event) {
        try {
            App.setRoot("MainWindow");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        EventBus.getDefault().register(this);
        parkingLotName = DataSingleton.getInstance().getDataName();

    }

}
