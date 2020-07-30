/*<![CDATA[*/
var culture = /*[[${culture}]]*/ 'default'; // 전체 데이터
    var tag_class = /*[[${tag_s}]]*/ 'default'; // 테마들
    var a_tag = []; /*]]>*/
    for (var i = 0; i < tag_class.length; i++) {
        a_tag.push([]); // 테마 수만큼 배열 생성
    }
    console.log(culture);
    for (var i = 0; i < culture.length; i++) {
        var info = JSON.parse(culture[i]); // 해당 테마의 정보들
        var idx = tag_class.indexOf(info.tag);
        if(!((info.tag=="")||(info.tag==null))){
            var idx = tag_class.indexOf(info.tag);
            a_tag[idx].push({
                k_name: info.k_name,
                e_name: info.e_name,
                gu: info.gu,
                dong: info.dong,
                k_describe: info.k_describe,
                e_describe: info.e_describe,
                tel: info.tel,
                img_url: info.img_url,
                tag: info.tag
            })
        }}
/*
$(function(){
    $('#name').html('tag1.html');
      $.ajax({
          type: "GET",
          crossOrigin: true,
          url: "resources/data.xlxs",
          success:function(data){
            var list = eval(data);
            var tag1 =[];
            
            for(var i=0; i<list.length; i++){
              if(list[i].TAG == "함께 걷기 좋은"){
                tag1.push({"k_name": list[i].K_NAME, "e_name": list[i].E_NAME, 
                            "tel": list[i].TEL, "k_des": list[i].K_DESCRIBE,
                            "e_des": list[i].E_DESCRIBE}); 
              }
            }
            for(var i=0; i<tag1.length; i++){
                $('#name'+i).html(tag1[i].k_name+'('+tag1[i].e_name+')'+'</br>'
                                +'전화번호: '+tag1[i].tel+'</br>'
                                +tag1[i].k_des+'</br>'+tag1[i].e_des );
            }         
                      
          }
      })
  })
*/