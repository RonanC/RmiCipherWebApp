<%@ include file="includes/header.jsp" %>

<div class="animated bounceInDown" style="font-size:48pt; font-family:arial; color:#990000; font-weight:bold">Vigenère Cipher Encrypter</div>

<br><br><br>
<p>This program can only crack words with a max length of 5 in a reasonable time.<br>If you choose a keyword of more then 5 characters, make sure to provide the keyword to the recipient.</p>
<p>Only letters will be kept; all punctuation, spaces and numbers will be removed.</p>
<br>
<table width="600" cellspacing="0" cellpadding="7" border="0" ng-controller="InfoController">
	<tr>
		<td valign="top">

			<form bgcolor="white" method="POST" action="doProcess" name="encryptForm">
				<fieldset>
					<legend><h3>Cipher Details</h3></legend>
				
					<b>Key word:</b> <input name="frmKeyWord" type="text" ng-model="keyWord" min="3" max="10" ng-minlength="3" ng-maxlength="10" required>
					<p/>

					<b>Enter Plain-Text:</b><br>
					<textarea name="frmInputText" ng-model="plainText" rows="10" cols="100"  wrap="soft" placeholder="Enter plain text here..." ng-minlength="10" ng-maxlength="10000"  min="10" max="10000" autofocus required></textarea>	
					<p/>
					
					<input name="frmTaskNum" type="hidden" value="-1">
					<input name="frmTaskType" type="hidden" value="0">
					<!-- 0 = encrypt -->


					<center><input class="btn btn-primary" type="submit" value="Encrypt Text" ng-disabled="encryptForm.$error.required || encryptForm.$error.minlength || encryptForm.$error.maxlength"></center>
					<!-- cipherForm.$error.required || cipherForm.$error.minlength -->
				</fieldset>							
			</form>	

		</td>
	</tr>
</table>

<%@ include file="includes/footer.jsp" %>

