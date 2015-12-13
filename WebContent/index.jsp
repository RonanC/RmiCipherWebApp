<%@ include file="includes/header.jsp"%>

<table width="600" cellspacing="0" cellpadding="7" border="0">
	<tr>
		<td valign="top">
			<h1 align="center">
				<img alt="cipher_img" src="images/cipher.png" width="50em"
					height="50em"> Vigenere Cipher
			</h1> <!-- 			<p>Welcome to the Cipher Web App!</p> --> <br> <br>
			<p>We provides services for:</p>
			<ul>
				<li>Encrypting plain text with the classic Vigenere cipher</li>
				<li>Decrypting cipher text with a known key</li>
				<li>Cracking encrypted text with brute force</li>
			</ul> <br>
			<h2 align="center">Options:</h2> 
			<a href="encrypt.jsp" class="btn btn-primary btn-large btn-block" role="button">Encrypt</a>
			<a href="decrypt.jsp" class="btn btn-primary btn-large btn-block" role="button">Decrypt</a>
			<a href="crack.jsp" class="btn btn-primary btn-large btn-block" role="button">Crack</a> <br>

		</td>
	</tr>
</table>

<%@ include file="includes/footer.jsp"%>

