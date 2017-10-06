<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8"/>
    <title></title>
  </head>
  <body>
    <?php
      require_once("connect.php");
      if(isset($_GET['airline']) && isset($_GET['flightnr'])) {
        $airlineid = $_GET['airline'];
        $flightnr = $_GET['flightnr'];

        $statement = $pdo->prepare("SELECT * FROM flights WHERE flightnr = :flightnr AND airline = :airline");
        $result = $statement->execute(array('flightnr' => $flightnr, 'airline' => $airlineid));
        $flight = $statement->fetch();

        if($flight) {
          $statement = $pdo->prepare("SELECT * FROM airlines WHERE id = :id");
          $result = $statement->execute(array('id' => $flight['airline']));
          $airline = $statement->fetch();

          $statement = $pdo->prepare("SELECT * FROM planes WHERE id = :id");
          $result = $statement->execute(array('id' => $flight['planetype']));
          $plane = $statement->fetch();

          $statement = $pdo->prepare("SELECT id,firstname,lastname,rownr,seatposition FROM passengers WHERE airline = :airline AND flightnr = :flightnr ORDER BY id");
          $result = $statement->execute(array("airline" => $airlineid, "flightnr" => $flightnr));

          while ($passenger = $statement->fetch()) {
	           $passengers[] = $passenger;
	         }
        } else {
          header("location: ");
        }
      } else {
        header("location: ");
      }
    ?>
    <table>
     <thead>
       <tr>
         <th>Fluginformationen</th>
         <th></th>
       </tr>
     </thead>
     <tbody>
       <tr>
         <td>Flugnummer:</td>
         <td><?php echo($airline['id']."-".$flight['flightnr']); ?></td>
       </tr>
       <tr>
         <td>Airline:</td>
         <td><?php echo($airline['name']); ?></td>
       </tr>
       <tr>
         <td>Abflugsflughafen:</td>
         <td><?php echo($flight['departure_airport']); ?></td>
       </tr>
       <tr>
         <td>Abflugszeit:</td>
         <td><?php echo(date("d.m.Y h:i", strtotime($flight['departure_time']))); ?></td>
       </tr>
       <tr>
         <td>Zielflughafen:</td>
         <td><?php echo($flight['destination_airport']); ?></td>
       </tr>
       <tr>
         <td>Ankuftszeit:</td>
         <td><?php echo(date("d.m.Y h:i", strtotime($flight['destination_time']))); ?></td>
       </tr>
       <tr>
         <td>Freie Plätze</td>
         <td><?php echo($plane['maxseats'] - count($passengers)); ?></td>
       </tr>
     </tbody>
   </table>

   <h3>Passagiere:</h3>
   <table>
     <thead>
       <tr>
           <th>Vorname</th>
           <th>Nachname</th>
           <th>Sitzplatz</th>
           <th>Passagiere entfernen</th>
       </tr>
     </thead>
     <tbody>
       <?php
         for($i = 0; $i < count($passengers); $i++) {
           echo "<tr>
             <td>".$passengers[$i][1]."</td>
             <td>".$passengers[$i][2]."</td>
             <td>".$passengers[$i][3]."".$passengers[$i][4]."</td>
             <td><a href='../kick/?id=".$passengers[$i][0]."&airline=".$airlineid."&flight=".$flightnr."'>Entfernen</a></td>
           </tr>";
         }
       ?>
     </tbody>
   </table>
  </body>
</html>
