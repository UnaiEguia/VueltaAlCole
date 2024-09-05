<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
<head>
    <title>Página Principal</title>
    <style>
        /* Estilos para la ventana modal */
        .modal {
            display: none;
            position: fixed;
            z-index: 1;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            overflow: auto;
            background-color: rgb(0,0,0);
            background-color: rgba(0,0,0,0.4);
            padding-top: 60px;
        }

        .modal-content {
            background-color: #fefefe;
            margin: 5% auto;
            padding: 20px;
            border: 1px solid #888;
            width: 80%;
            max-width: 300px;
        }

        .close {
            color: #aaa;
            float: right;
            font-size: 28px;
            font-weight: bold;
        }

        .close:hover,
        .close:focus {
            color: black;
            text-decoration: none;
            cursor: pointer;
        }
    </style>
</head>
<body>

<!-- Menú de navegación -->
<nav>
    <ul>
        <li>
            <a href="MenuProductos">Productos</a>
        </li>
        <li>
            <a href="#" id="categoriaLink">Categoría</a>
        </li>
    </ul>
</nav>

<!-- Ventana Modal -->
<div id="passwordModal" class="modal">
    <div class="modal-content">
        <span class="close">&times;</span>
        <h2>Introduce la contraseña</h2>
        <form id="passwordForm" method="post" action="MenuCategoria">
            <label for="password">Contraseña:</label>
            <input type="password" name="password" autofocus="autofocus" id="password">
            <input type="submit" value="Entrar" >
        </form>
        <p id="errorMsg" style="color: red; display: none;">Contraseña incorrecta, intenta de nuevo.</p>
    </div>
</div>

<script>
    // Obtener elementos
    var modal = document.getElementById("passwordModal");
    var btn = document.getElementById("categoriaLink");
    var span = document.getElementsByClassName("close")[0];
    var errorMsg = document.getElementById("errorMsg");
    
    // Cuando el usuario hace clic en el enlace de Categoría
    btn.onclick = function() {
        modal.style.display = "block";
        document.getElementById("password").focus(); // Enfocar el campo de contraseña
    }

    // Cuando el usuario hace clic en la "X" para cerrar
    span.onclick = function() {
        modal.style.display = "none";
    }

    // Cuando el usuario hace clic fuera del modal
    window.onclick = function(event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }

    // Enviar contraseña al servidor para validación
    function submitPassword() {
        var password = document.getElementById("password").value;

        // Crear una solicitud AJAX
        var xhr = new XMLHttpRequest();
        xhr.open("POST", "MenuCategoria", true);
        xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xhr.onreadystatechange = function() {
            if (xhr.readyState == 4 && xhr.status == 200) {
                if (xhr.responseText.trim() === "success") {
                    window.location.href = "MenuCategoria";
                } else {
                    errorMsg.style.display = "block";
                }
            }
        };
        xhr.send("password=" + encodeURIComponent(password));
    }
</script>

</body>
</html>
