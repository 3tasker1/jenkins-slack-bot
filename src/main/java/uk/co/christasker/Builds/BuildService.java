package uk.co.christasker.Builds;

import com.github.seratch.jslack.api.model.dialog.DialogElement;
import com.github.seratch.jslack.api.model.dialog.DialogOption;
import com.github.seratch.jslack.api.model.dialog.DialogSelectElement;
import com.github.seratch.jslack.api.model.dialog.DialogTextElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class BuildService {

  List<Build> knownBuilds;

  public BuildService() {
    knownBuilds = new ArrayList<>();

    //In the real world this should use some config
    DialogSelectElement patchLabel = DialogSelectElement.builder()
      .label("Patch Label")
      .name("PATCH_LABEL")
      .value("PATCHTRAINING")
      .options(Arrays.asList(
        DialogOption.builder().value("PATCHTRAINING").label("Training - Patch").build()
        , DialogOption.builder().value("POSTPATCHTRAINING").label("Training - Post Patch").build()
      ))
      .build();

    DialogSelectElement patchDir = DialogSelectElement.builder()
      .label("Patch Directory")
      .name("PATCH_DIR")
      .value("CorePatches")
      .options(Arrays.asList(
        DialogOption.builder().value("CorePatches").label("CorePatches").build()
        , DialogOption.builder().value("CorePatchesPostLoad").label("CorePatchesPostLoad").build()
      ))
      .build();

    DialogTextElement jobNo = DialogTextElement.builder()
      .label("Job Number")
      .name("JOB_NO")
      .hint("JIRA Job No")
      .maxLength(50)
      .placeholder("TRAIN-999")
      .build();

    DialogTextElement patchDesc = DialogTextElement.builder()
      .label("Patch Description")
      .name("PATCH_DESC")
      .hint("Patch description")
      .maxLength(150)
      .build();

    List<DialogElement> elements = Arrays.asList(
      patchLabel
      , patchDir
      , patchDesc
      , jobNo
    );

    Build build = new Build("Patch Stubber", "view/Codesource/job/p4_codesource_patch_stubber", elements);
    knownBuilds.add(build);

  }

  public Optional<Build> getBuild(String nameLike) {
    return knownBuilds
      .stream()
      .filter(build -> nameLike.toUpperCase().contains(build.getName().toUpperCase()))
      .findFirst();
  }



}
