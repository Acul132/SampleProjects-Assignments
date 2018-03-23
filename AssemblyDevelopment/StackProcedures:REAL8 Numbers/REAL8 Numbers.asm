;Assignment4 Question4      (question4.asm)
;This question was completed by Joshua Renelli and Paolo Scola


include		/irvine/irvine32.inc
includelib	/irvine/irvine32.lib
includelib	/irvine/user32.lib
includelib	/irvine/kernel32.lib

.data
A	REAL8	2.
B	REAL8	2.
D	REAL8	3.
E	REAL8	2.
F	REAL8	2.
G	REAL8	1.
H	REAL8	3.
I	REAL8	2.
J	REAL8	5.
K	REAL8	2.
Z	REAL8	?
.code
main PROC
	fld		A
	fld		B
	fld		D
	fmul
	fld		E
	fdiv
	fadd
	fld		F
	fld		G
	fld		H
	fabs
	fld		I
	fsub
	fmul
	fld		J
	fdiv
	fsub
	fld		K
	fmul
	fadd	
;Z = A + B * D / E + ( F - G * ( abs(H) - I ) / J ) * K
	fst		Z
	exit
main ENDP
END main