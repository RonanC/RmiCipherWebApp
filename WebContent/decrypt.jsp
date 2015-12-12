<%@ include file="includes/header.jsp" %>

<div class="animated bounceInDown" style="font-size:48pt; font-family:arial; color:#990000; font-weight:bold">Vigenere Cipher Breaker</div>

</p>&nbsp;</p>&nbsp;</p>

<table width="600" cellspacing="0" cellpadding="7" border="0" ng-controller="InfoController">
	<tr>
		<td valign="top">

			<form bgcolor="white" method="POST" action="doProcess" name="cipherForm">
				<fieldset>
					<legend><h3>Cipher Details</h3></legend>
				
					<b>Maximum Vigenere Key Length:</b> <input name="frmMaxKeyLength" type="number" size="2" ng-value="3" value="3" min="3" max="10" ng-minlength="3" ng-maxlength="10" required>
					<p/>

					<b>Enter Cipher-Text:</b><br>
					<textarea name="frmCypherText" rows="10" cols="100"  wrap="soft" placeholder="Enter cipher text here..." ng-minlength="10" ng-maxlength="100"  min="10" max="100" ng-model="cipherText" autofocus required></textarea>	
					<p/>


					<center><input class="btn btn-primary" type="submit" value="Crack Cypher" ng-disabled="cipherForm.$error.required || cipherForm.$error.minlength || cipherForm.$error.maxlength"></center>
					<!-- cipherForm.$error.required || cipherForm.$error.minlength -->
				</fieldset>							
			</form>	

		</td>
	</tr>
</table>

<%@ include file="includes/footer.jsp" %>

