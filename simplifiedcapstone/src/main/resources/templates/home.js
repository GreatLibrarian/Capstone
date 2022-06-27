      // Internal scripting to check if email is in email format
      function checkIfEmailInString(text) {
        var re =
          /(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))/;
        let bool = re.test(text);
        if (bool) {
          return bool;
        } else {
          let node = document.getElementById("emailerror");
          node.innerText("Enter a valid Email Address.");
          return bool;
        }
      }