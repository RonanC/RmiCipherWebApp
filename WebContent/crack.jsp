<%@ include file="includes/header.jsp" %>

<div class="animated bounceInDown" style="font-size:48pt; font-family:arial; color:#990000; font-weight:bold">Vigenère Cipher Cracker</div>

<br><br><br>
<p>This program can only crack words with a max key length of 5 in a reasonable time.<br>The program is limited to a maximum key length of 5 as to not block the system.</p>
<br>
<table width="600" cellspacing="0" cellpadding="7" border="0" ng-controller="InfoController">
	<tr>
		<td valign="top">

			<form bgcolor="white" method="POST" action="doProcess" name="cipherForm">
				<fieldset>
					<legend><h3>Cipher Details</h3></legend>
				
					<b>Maximum Vigenere Key Length:</b> <input name="frmMaxKeyLen" type="number" size="2" ng-value="3" value="3" min="3" max="5" ng-minlength="3" ng-maxlength="5" required>
					<p/>

					<b>Enter Cipher-Text:</b><br>
					<textarea name="frmInputText" ng-model="cipherText" rows="10" cols="100"  wrap="soft" placeholder="Enter cipher text here..." ng-minlength="20" ng-maxlength="10000"  min="20" max="10000" autofocus required></textarea>	
					<p/>
					
					<input name="frmTaskNum" type="hidden" value="-1">
					<input name="frmTaskType" type="hidden" value="2">
					<!-- 2 = crack -->
					
					<center><input class="btn btn-primary" type="submit" value="Crack Cypher" ng-disabled="cipherForm.$error.required || cipherForm.$error.minlength || cipherForm.$error.maxlength"></center>
					<!-- cipherForm.$error.required || cipherForm.$error.minlength -->
				</fieldset>							
			</form>	

		</td>
	</tr>
</table>

<%@ include file="includes/footer.jsp" %>

