package uk.co.christasker.Builds;

import com.github.seratch.jslack.api.model.dialog.Dialog;
import com.github.seratch.jslack.api.model.dialog.DialogElement;

import java.util.List;

public class Build {

  final String name;
  final String jenkinsName;
  List<DialogElement> dialogElements;


  public Build(String name, String jenkinsName, List<DialogElement> dialogElements) {
    this.name = name;
    this.jenkinsName = jenkinsName;
    this.dialogElements = dialogElements;
  }

  public String getName() {
    return name;
  }

  public String getJenkinsName() {
    return jenkinsName;
  }

  public List<DialogElement> getDialogElements() {
    return dialogElements;
  }

  public void setDialogElements(List<DialogElement> dialogElements) {
    this.dialogElements = dialogElements;
  }

  public Dialog getDialog() {
    Dialog dialog = Dialog.builder()
      .title(getName())
      .submitLabel("Submit")
      .callbackId(getJenkinsName())
      .elements(getDialogElements())
      .build();
    return dialog;
  }

}
