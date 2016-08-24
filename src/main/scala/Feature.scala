case class Feature(name:String, keywords:Set[String], var value:String)

class LevelOfItem(var newValue:String="")            extends Feature("Уровень предмета",Set("уровень предмета"),newValue)
class Water(var newValue:String="")                    extends Feature("Вода",Set("вода","воду"),newValue)
class Fire(var newValue:String="")                     extends Feature("Огонь",Set("огонь"),newValue)
class Wind(var newValue:String="")                     extends Feature("Воздух",Set("воздух"),newValue)
class Earth(var newValue:String="")                    extends Feature("Земля",Set("земля", "землю"),newValue)
class Endurance(var newValue:String="")                extends Feature("Выносливость",Set("выносливость"),newValue)
class Accuracy(var newValue:String="")                 extends Feature("Меткость",Set("меткость"),newValue)
class Force(var newValue:String="")                    extends Feature("Сила",Set("сила", "силу"),newValue)
class Dexterity(var newValue:String="")                extends Feature("Ловкость",Set("ловкость"),newValue)
class Dignity(var newValue:String="")                  extends Feature("Титул",Set("титул"),newValue)
class Degree(var newValue:String="")                   extends Feature("Степень",Set("степень"),newValue)
class Prana(var newValue:String="")                    extends Feature("Прана",Set("прана", "прану"),newValue)
class Magic_attack(var newValue:String="")             extends Feature("Магическая атака",Set("ма"),newValue)
class Magic_defense(var newValue:String="")            extends Feature("Магическая защита",Set("мз"),newValue)
class Physic_attack(var newValue:String="")            extends Feature("Физическая атака",Set("фа"),newValue)
class Physic_defense(var newValue:String="")           extends Feature("Физическая защита",Set("фз"),newValue)
class Lifetime(var newValue:String="")                 extends Feature("Срок жизни",Set("срок жизни"),newValue)
class Specialization(var newValue:String="")           extends Feature("Специализация",Set("специализация", "cпециализация"),newValue) // спасибо Gamexp! за английскую C
class Level_of_Specialization(var newValue:String="")  extends Feature("Уровень специализации",Set("уровень специализации"),newValue)
class Health(var newValue:String="")                   extends Feature("Здоровье",Set("здоровье"),newValue)
class Distance(var newValue:String="")                 extends Feature("Дистанция",Set("дистанция"),newValue)
class Cooldown(var newValue:String="")                 extends Feature("Перезарядка",Set("перезарядка", "перезаряд"),newValue)
class Karma(var newValue:String="")                    extends Feature("Карма",Set("карма"),newValue)
class Tradability(var newValue:String="")              extends Feature("Передаваемость",Set("передаваемость"),newValue)
class Validity(var newValue:String="")                 extends Feature("Срок действия",Set("срок действия"),newValue)
class Quantity(var newValue:String="")                 extends Feature("Количество",Set("количество"),newValue)
class Durability(var newValue:String="")               extends Feature("Прочность",Set("прочность"),newValue)
class StartCharges(var newValue:String="")             extends Feature("Начальное количество зарядов",Set("начальное количество зарядов"),newValue)
class MaxCharges(var newValue:String="")               extends Feature("Максимальное количество зарядов",Set("максимальное количество зарядов"),newValue)
class Description(var newValue:String="")              extends Feature("Описание",Set(),newValue)

class FeatureList(name:String, keywords:Set[String], var features: Set[Feature])

case class Requirements(set:Set[Feature])              extends FeatureList("Требования",Set("требования"),set)
case class Effect(set:Set[Feature])                    extends FeatureList("Эффект",Set("эффект"),set)
case class DescriptionList(set:Set[Feature])           extends FeatureList("Описание", Set("Описание:"),set)

object Feature {
  val AllFeatures = Set[Feature](
    new LevelOfItem,new Water, new Fire, new Wind,
    new Earth, new Endurance, new Accuracy, new Force,
    new Dexterity, new Dignity, new Degree, new Prana,
    new Magic_attack, new Magic_defense, new Physic_attack, new Physic_defense,
    new Lifetime, new Specialization, new Level_of_Specialization, new Health,
    new Distance, new Cooldown, new Karma, new Tradability,
    new Validity, new Quantity, new Durability, new StartCharges,
    new MaxCharges, new Description
  )

  def getFeatureByKeyword(keyword: String):Feature = {
    val filtered = AllFeatures.filter( feature => feature.keywords.contains(keyword) )
    val result = filtered.head
    result
  }

}