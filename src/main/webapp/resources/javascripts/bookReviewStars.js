/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function(){
 
});

function fillUpTo(num)
{
    for(var i = 1; i <= num; i++)
    {
        $('#star_' + i).removeClass('fa-star-o');
        $('#star_' + i).addClass('fa-star');
    }
    
    if(num < 5){
        for(var i = num + 1; i <=5;i++){
            $('#star_' + i).removeClass('fa-star');
            $('#star_' + i).addClass('fa-star-o');
        }
    }
    
    var ratingInput = $('#add-review-form\\:rating_value').val(num);
    console.log(ratingInput.val());
}
