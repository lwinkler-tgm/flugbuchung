<?php
  require_once("connect.php");
  if(isset($_GET['id']) && isset($_GET['airline']) && isset($_GET['flight'])) {
    $id = $_GET['id'];
    $airline = $_GET['airline'];
    $flight = $_GET['flight'];

    $statement = $pdo->prepare("SELECT * FROM passengers WHERE id = :id AND airline = :airline AND flightnr = :flightnr");
    $result = $statement->execute(array('id' => $id, 'airline' => $airline, 'flightnr' => $flight));
    $passenger = $statement->fetch();

    if($passenger) {
      $statement = $pdo->prepare("DELETE FROM passengers WHERE id = :id AND flightnr = :flightnr");
      $result = $statement->execute(array('id' => $id, 'flightnr' => $flight));

      if($result) {
        header("location: outputs.php?airline=".$airline."&flight=".$flight."");
      } else {
        header("location: outputs.php?airline=".$airline."&flight=".$flight."");
      }
    } else {
      header("location: outputs.php?airline=".$airline."&flight=".$flight."");
    }
  } else {
    echo("<script>window.history.back();</script>");
  }
?>
