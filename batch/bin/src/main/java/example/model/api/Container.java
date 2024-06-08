package example.model.api;

import lombok.*;

import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;

@Data
public class Container
{
  Portal portal;
  Topics topics;

  private void parceWord(HashMap<String, String> keyword, String word)
  {

    if(!keyword.containsKey(word))
    {
      keyword.put(word, word);
    }

  }

  private void parcePortal(HashMap<String, String> keyword)
  {
    for(String word: portal.getName().split("-"))
    {
      parceWord(keyword, word);
    }

  }

  private void parceTopic(HashMap<String, String> keyword)
  {
    for(String word: topics.getNames())
    {
      parceWord(keyword, word);
    }

  }

  public void process()
  {
    HashMap<String, String> keyword = new HashMap<String, String>();
    parcePortal(keyword);
    parceTopic(keyword);
  }
	
}
