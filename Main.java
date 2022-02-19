package application;
	
import java.util.ArrayList;
import java.util.Optional;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.text.TextAlignment;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;

/**
 * @author Anwar
 *
 */

public class Main extends Application {
	
	
	Label menulbl=new Label("قائمة الديون");

	TableView<Account> menuTv=new TableView<Account>();
	TableColumn<Account ,String> nameTc=new TableColumn<Account ,String>("name");
	TableColumn<Account ,Integer> purchaseTc=new TableColumn<Account ,Integer>("amount");

	Button addAccountBt=new Button("اضافة حساب");
	
	Label accountNamelbl=new Label("اسم المدين الكامل");
	
	TableView<Purchase> accountTv=new TableView<Purchase>();
	TableColumn<Purchase ,Integer> purchasesTvCol=new TableColumn<Purchase ,Integer>("total");
	TableColumn<Purchase ,String> dateTvCol=new TableColumn<Purchase ,String>("date");
	
	Button nextBt=new Button("التالي");
	Button previusBt=new Button("االسابق");
	Button firstBt=new Button("االأول");
	Button lastBt=new Button("االأخير");
	
	Button deleteAccountBt=new Button("احذف الحساب");
	Button zeroAccountBt=new Button("تصفير الحساب");
	Button purchaseBt=new Button("شراء");
	Button payBt=new Button("تسديد");
	
	Label totallbl=new Label("المبلغ الكلي=");
	
	
	  //الرئيسية الحاوية   
	Pane root;
	//الحسابات قائمة
	ArrayList<Account> accounts=new ArrayList<Account>();
	//نافذة لاظهار رسالة خطأ عند ادخال معلومات خطأ
	
	Alert addNewAccountAlert=new Alert(AlertType.INFORMATION);
	
	int index=0;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			root = new Pane();
			Scene scene = new Scene(root,600,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}

		

		
		setPane();
		// اضافة كافة المكونات الى الحاوية الرئيسية
		root.getChildren().addAll(menulbl,menuTv,addAccountBt,accountTv,accountNamelbl,nextBt,
				previusBt,firstBt,lastBt,zeroAccountBt,payBt,purchaseBt,deleteAccountBt,totallbl);
		

		
	}
	
	
	
	public static void main(String[] args) {
		Application.launch(args);
	}
	

	
	
	// تحديد الابعاد ومواقع كاف
	void setPane() {
		
		//بدلا من استخدام سنستخدم تحديد كل ابعاد واماكن المحتويات 
		
		menuTv.getColumns().addAll(nameTc,purchaseTc);
		accountTv.getColumns().addAll(purchasesTvCol,dateTvCol);
		
		menulbl.setStyle("-fx-background-color:yellow;-fx-font-size:19;"
				+ "-fx-fontweight:bold;-fx-fontstyle:regular;-fx-fontfamily:TimesNewRoman;-fx-text-alignment:center;");
		menulbl.setTextAlignment(TextAlignment.CENTER);
		menulbl.setTranslateX(60);
		menulbl.setTranslateY(5);
		menulbl.setPrefSize(100, 20);
		
		
		menuTv.setTranslateX(5);
		menuTv.setTranslateY(40);
		menuTv.setPrefSize(240, 400);
		
		
		addAccountBt.setTranslateX(70);
		addAccountBt.setTranslateY(445);
		addAccountBt.setPrefSize(100,25);
		
		accountNamelbl.setStyle("-fx-background-color:white;-fx-font-size:19;");
		accountNamelbl.setTextAlignment(TextAlignment.CENTER);
		accountNamelbl.setTranslateX(360);
		accountNamelbl.setTranslateY(5);
		accountNamelbl.setPrefSize(120, 20);
		
		
		accountTv.setTranslateX(340);
		accountTv.setTranslateY(40);
		accountTv.setPrefSize(160, 350);
		
		nextBt.setTranslateX(250);
		nextBt.setTranslateY(40);
		nextBt.setPrefSize(70,25);
		
		 
		previusBt.setTranslateX(250);
		previusBt.setTranslateY(70);
		previusBt.setPrefSize(70,25);
		
		firstBt.setTranslateX(250);
		firstBt.setTranslateY(100);
		firstBt.setPrefSize(70,25);
		
		lastBt.setTranslateX(250);
		lastBt.setTranslateY(130);
		lastBt.setPrefSize(70,25);
		
		
		totallbl.setTranslateX(340);
		totallbl.setTranslateY(395);
		totallbl.setPrefSize(150, 20);
		
		purchaseBt.setTranslateX(505);
		purchaseBt.setTranslateY(40);
		purchaseBt.setPrefSize(50, 25);
		
		payBt.setTranslateX(505);
		payBt.setTranslateY(70);
		payBt.setPrefSize(50, 25);
			
		zeroAccountBt.setTranslateX(505);
		zeroAccountBt.setTranslateY(100);
		zeroAccountBt.setPrefSize(90, 25);
		
		deleteAccountBt.setTranslateX(505);
		deleteAccountBt.setTranslateY(130);
		deleteAccountBt.setPrefSize(90, 25);
		
		//سوف نعمل هاندلينك لزر اضافة حساب والذي عند الضغط عليه يتم تنفيذ عدة اجراءات وحسب المطلوب
		addAccountBt.setOnMouseClicked(e -> {

			
			Dialog dialog=new TextInputDialog();
			dialog.setTitle("ادخل الأسم هنا");
			dialog.setHeaderText("ااسم الحساب الكامل");
			
			Optional<String> result=dialog.showAndWait();
			String entered="none";
			if(result.isPresent()) {
				entered=result.get();
				addAccount(entered);
			}
			
			
		});
		
		purchaseBt.setOnMouseClicked(e -> {
			Dialog dialog=new TextInputDialog();
			dialog.setTitle("شراء");
			dialog.setHeaderText("اكتب مبلغ الشراء");
			
			Optional<String> result=dialog.showAndWait();
			String entered="none";
			if(result.isPresent()) {
				entered=result.get();
				try {
				int purchaseAmount=Integer.parseInt(entered);
				accounts.get(index).purchase(purchaseAmount);
				
				}catch(Exception ex) {
					addNewAccountAlert.showAndWait();
					addNewAccountAlert.setAlertType(AlertType.ERROR);
					addNewAccountAlert.setContentText("Invalid input \""+entered+"\"");
					ex.printStackTrace();
				}
				viewMenu();
				viewAccount(index);
			}
			
		});
		payBt.setOnMouseClicked(e ->{
			
			Dialog dialog=new TextInputDialog();
			dialog.setTitle("تسديد");
			dialog.setHeaderText("اكتب المبلغ");
			
			Optional<String> result=dialog.showAndWait();
			String entered="none";
			if(result.isPresent()) {
				entered=result.get();
				try {
				int payAmount=Integer.parseInt(entered);
				accounts.get(index).pay(payAmount);
				}catch(Exception ex) {
					addNewAccountAlert.showAndWait();
					addNewAccountAlert.setAlertType(AlertType.ERROR);
					addNewAccountAlert.setContentText("Invalid input \""+entered+"\"");
					ex.printStackTrace();
				}
				viewMenu();
				viewAccount(index);
			}
		});
		
		zeroAccountBt.setOnMouseClicked(e ->{
			accounts.get(index).zero();
			viewMenu();
			viewAccount(index);
		});
		
		deleteAccountBt.setOnMouseClicked(e ->{
			accounts.remove(index);
			index=0;
			viewMenu();
			viewAccount(index);
		});
		
		nextBt.setOnMouseClicked(e ->{
			if(index < accounts.size()-1) {
			index++;
			viewAccount(index);
			viewMenu();
			menuTv.getSelectionModel().select(index);
			}
		});
		
		previusBt.setOnMouseClicked(e ->{
			if(index > 0) {
			index--;
			viewAccount(index);
			viewMenu();
			menuTv.getSelectionModel().select(index);
			}
		});
		lastBt.setOnMouseClicked(e ->{
			index=accounts.size()-1;
			viewAccount(index);
			viewMenu();
			menuTv.getSelectionModel().select(index);
		});
		firstBt.setOnMouseClicked(e ->{
			index=0;
			viewAccount(index);
			viewMenu();
			menuTv.getSelectionModel().select(index);
		});
		
		nameTc.setCellValueFactory(new PropertyValueFactory<Account ,String>("name"));
		purchaseTc.setCellValueFactory(new PropertyValueFactory<Account ,Integer>("total"));
		
		purchasesTvCol.setCellValueFactory(new PropertyValueFactory<Purchase ,Integer>("amount"));
		dateTvCol.setCellValueFactory(new PropertyValueFactory<Purchase ,String>("date"));
		
		
		
        ObservableList<Account> items=menuTv.getSelectionModel().getSelectedItems();
        
        items.addListener(
        	new ListChangeListener<Account>() {
        		@Override
        		public void onChanged(
                	Change<? extends Account> change){
        				viewAccount(accounts.indexOf(items.get(0)));
        			}
        		
        	});
	}
	
	void addAccount(String name) {
		accounts.add(new Account(name));
		viewMenu();
		viewAccount(accounts.size()-1);
	}
	
	void viewMenu() {
		ObservableList<Account> data=FXCollections.observableArrayList(accounts);
		menuTv.setItems(data);
		
	
	}
	
	void viewAccount(int index) {
		ObservableList<Purchase> data=FXCollections.observableArrayList(accounts.get(index).getPurchases());
		accountTv.setItems(data);
		totallbl.setText("المبلغ الكلي="+accounts.get(index).getTotal());
		accountNamelbl.setText(accounts.get(index).getName());
	}
	
	
}

