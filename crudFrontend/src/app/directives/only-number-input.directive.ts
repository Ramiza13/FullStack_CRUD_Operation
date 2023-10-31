import { Directive, ElementRef, HostListener, OnInit } from '@angular/core';

@Directive({
  selector: '[appOnlyNumberInput]'
})
export class OnlyNumberInputDirective implements OnInit {

  private regex: RegExp = new RegExp('^[0-9]*$');
  constructor(private elementRef: ElementRef) { }

  ngOnInit() {

  }

  // @HostListener('keypress', ['$event']) onKeypress(event:  { which: any; keyCode: any; }) : boolean {  //event: Event
  //   const charCode = (event.which)? event.which : event.keyCode;

  //   if(charCode > 31 && (charCode<48 || charCode>57))
  //   {
  //     console.log('charCode ' + charCode + 'is restricted');
  //     keypress: false;
  //     return false;
  //   }

  //   return true;
  // }

  @HostListener('keypress', ['$event']) onKeypress(event: KeyboardEvent) {  //event: Event
    const inputValue: String = this.elementRef.nativeElement.value.concat(event.key);  //NOTE: Native element gives a direct access to the DOM
    if(inputValue && !String(inputValue).match(this.regex)) {
      event.preventDefault();
    }
    return;
  }
}
