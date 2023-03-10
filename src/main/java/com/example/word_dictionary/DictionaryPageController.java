package com.example.word_dictionary;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class DictionaryPageController {

    private DictionarySaver db;

    @FXML
    TextField search;
    @FXML
    TextField newword;
    @FXML
    TextField meaning;

    @FXML
    TextField findword;

    @FXML
    public void SearchButton(MouseEvent event) throws ClassNotFoundException, IOException {
        try {
            db=new DictionarySaver();
            db.deserializeHashMap();

            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Dictionary");

            if(search.getText().equals("")){
                alert.setContentText("Please Enter a word to search");
                alert.showAndWait();
            }

            else if(db.getDictionaryList().containsKey(search.getText().toLowerCase())) {
                alert.setContentText("The word is available in the Dictionary");
                alert.showAndWait();
            }
            else{
                alert.setContentText("The word is not available in the Dictionary");
                alert.showAndWait();
            }

        }
        catch(IOException ex){
            db.serializeHashMap();
            System.out.println("Exception Resolved");
        }




    }

    @FXML
    public void Add(MouseEvent event) throws IOException, ClassNotFoundException {

        db=new DictionarySaver();
        db.deserializeHashMap();

        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Dictionary");

        if (newword.getText().equals("") || meaning.getText().equals(""))
        {
            alert.setContentText("Please enter word/meaning to Add to the Dictionary");
            alert.showAndWait();
        }
        else if(db.getDictionaryList().containsKey(newword.getText().toLowerCase())){
            alert.setContentText("The word is already available in the Dictionary");
            alert.showAndWait();
        }
        else
        {
            db.getDictionaryList().put(newword.getText().toLowerCase(),meaning.getText());
            db.serializeHashMap();

            alert.setContentText("New Word is Added to the Dictionary");
            alert.showAndWait();

        }


    }

    @FXML
    public void Meaning(MouseEvent event) throws IOException, ClassNotFoundException {

        db=new DictionarySaver();
        db.deserializeHashMap();

        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Meaning");

        if(findword.getText().equals("")){
            alert.setContentText("Please Enter a word to find meaning ");
            alert.showAndWait();
        }

        else if(db.getDictionaryList().containsKey(findword.getText())){
//            System.out.println(db.getDictionaryList().get(findword.getText()));
            alert.setContentText(db.getDictionaryList().get(findword.getText().toLowerCase()));
            alert.showAndWait();
        }
        else{
            alert.setContentText("This word is not available in the Dictionary");
            alert.showAndWait();

        }
    }
}
