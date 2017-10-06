<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Flugbuchung</title>
    </head>
    <body>
      <?php
      require_once('connect.php');

      if(isset($_GET['search'])) {
        $airline = $_POST['airline'];
        $flightnr = $_POST['flightnr'];

        $statement = $pdo->prepare("SELECT * FROM flights WHERE flightnr = :flightnr AND airline = :airline");
        $result = $statement->execute(array('flightnr' => $flightnr, 'airline' => $airline));
        $flight = $statement->fetch();

        if($flight) {
          header("location: outputs.php?airline=".$airline."&flightnr=".$flightnr);
        } else {
          echo("flug existiert nicht!");
        }
      }


      ?>

      <form action="?search=1" method="POST">
        <input type="text" id="airline" name="airline" placeholder="Airline" />
        <input type="text" id="flightnr" name="flightnr" placeholder="Flugnummer" />
        <button type="submit">Suchen</button>
      </form>

    </body>
</html>
