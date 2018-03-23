;Question2      (question2.asm)
;Author: Joshua Renelli

include		/irvine/irvine32.inc
includelib	/irvine/irvine32.lib
includelib	/irvine/user32.lib
includelib	/irvine/kernel32.lib

.data

x	DWORD	22222222h,22222222h,11111111h,11111111h
y	DWORD	11111111h,11111111h,33333333h,33333333h
z	DWORD	?,?,?,?
w	DWORD	?,?,?,?
seperation	byte	"h ",0		;Seperation between 32bit hexadecimal values

.code
main PROC
;Adding x and y, dealing with overflow
;Index 0
	mov		eax,[x]
	add		eax,[y]
	mov		[z],eax
;Index 1
	mov		eax,[x+4]
	adc		eax,[y+4]
	mov		[z+4],eax
;Index 2
	mov		eax,[x+8]
	adc		eax,[y+8]
	mov		[z+8],eax
;Index 3
	mov		eax,[x+12]
	adc		eax,[y+12]
	mov		[z+12],eax

;Subtracting x and y, dealing with overflow
;Index 0
	mov		eax,[x]
	sub		eax,[y]
	mov		[w],eax
;Index 1
	mov		eax,[x+4]
	sub		eax,[y+4]
	mov		[w+4],eax
;Index 2
	mov		eax,[x+8]
	sub		eax,[y+8]
	mov		[w+8],eax
;Index 3
	mov		eax,[x+12]
	sub		eax,[y+12]
	mov		[w+12],eax

;Outputing to screen
	xor		ecx,ecx
	mov		edx, OFFSET seperation
OutputZ:
	mov		eax,z[ecx]
	call		WriteHex
	call		WriteString
	add		ecx,4
	cmp		ecx,12
	jna		OutputZ		;Loops to output each 32bit section

	call		Crlf
	xor		ecx,ecx
OutputW:
	mov		eax,w[ecx]
	call		WriteHex
	call		WriteString
	add		ecx,4
	cmp		ecx,12
	jna		OutputW		;Loops to output each 32bit section
	exit
main ENDP
END main
