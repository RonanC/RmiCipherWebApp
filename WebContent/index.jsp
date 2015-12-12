<%@ include file="includes/header.jsp"%>

<table width="600" cellspacing="0" cellpadding="7" border="0">
	<tr>
		<td valign="top">
			<h1 align="center">
				<img alt="cipher_img" src="images/cipher.png" width="50em"
					height="50em"> Vigenere Cipher
			</h1> <!-- 			<p>Welcome to the Cipher Web App!</p> -->
			<br><br>
			<p>We provides services for:</p>
			<ul>
				<li>Encrypting data with the classic Vigenere cipher</li>
				<li>Brute force attacking data encrypted with the Vigenere
					cipher</li>
			</ul> <br>
			<h2 align="center">Options:</h2> <a href="encrypt.jsp"
			class="btn btn-primary btn-large btn-block" role="button">Encrypt</a>
			<a href="decrypt.jsp" class="btn btn-primary btn-large btn-block"
			role="button">Decrypt</a> <br>


		</td>
	</tr>
</table>

<%@ include file="includes/footer.jsp"%>

